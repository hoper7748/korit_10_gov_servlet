package com.korit.servletstudy.Login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class ResponseEntity<T> {
    private int status;
    private T body;

    public void response(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(status);            // 인증 실패 권한 없음이란 뜻의 에러 코드
        httpResponse.setContentType("application/json");
//        Map<String,Object> responseMap = Map.of(
//                "code", 403,
//                "message", message
//        );


        httpResponse.getWriter().println(JsonParserUtil.stringify(this));

    }
}
