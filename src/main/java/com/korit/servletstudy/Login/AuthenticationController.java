package com.korit.servletstudy.Login;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/api/auth/signin")
public class AuthenticationController  extends HttpServlet {
//    private String loginUsername = "abcd";
//    private String loginPassword = "1234";


    private User loginUser = User.builder()
            .id(1)
            .username("abcd")
            .password("1234")
            .name("AAA")
            .build();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = JsonParserUtil.getJson(req);
        Map<String,Object> requestBody = JsonParserUtil.parser(json);
        System.out.println(requestBody);
        if(!loginUser.getUsername().equals(requestBody.get("username"))){
            // 같은지 비교하여 로그인 확인하기;
            errorResponse(resp, "사용자 정보가 일치하지 않습니다.");
        }
//        System.out.println(loginUser)
    }

    private void errorResponse(HttpServletResponse resp, String message) throws IOException{
        resp.setStatus(403);            // 인증 실패 권한 없음이란 뜻의 에러 코드
        resp.setContentType("application/json");
        Map<String,Object> responseMap = Map.of(
                "code", 403,
                "message", message
        );
        resp.getWriter().println(JsonParserUtil.stringify(responseMap));
    }


}
