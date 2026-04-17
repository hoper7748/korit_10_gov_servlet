package com.korit.servletstudy.Login;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/api/*")
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("전처리");
        HttpServletRequest httpreq = (HttpServletRequest) request;
        String uri = httpreq.getRequestURI();
        String projectNameIgnoreUri = uri.substring(uri.indexOf("/",1));
        if(projectNameIgnoreUri.startsWith("/api/auth")){
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = httpreq.getSession();
        Object authAttribute = session.getAttribute("authentication");
        System.out.println(authAttribute);
        if(authAttribute == null){                      // null 이면 로그인 되지 않은 상태!!
            ResponseEntity.builder()
                    .status(403)
                    .body("로그인 후 이용가능합니다.")
                    .build()
                    .response(response);
            return ;                                    // 재귀 탈출
        }
        chain.doFilter(request, response);
    }

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
}

