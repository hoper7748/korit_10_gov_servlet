package com.korit.servletstudy.Login;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            .email("abc@gmail.com")
//            .role("")
            .build();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String json = JsonParserUtil.getJson(req);
        Map<String,Object> requestBody = JsonParserUtil.parser(json);
        System.out.println(requestBody);
        // or연산이 문제가 되는 이유
        // db에서 가져오는 연산이 없어서 체크가 안되는데,
        // DB에서 가져오는 과정을 거치면 현재 가져온 데이터가 null인가를 체크한다.
        // null인 체크를 한 객체가 or연산으로 체크를 시작하면 런타임 에러가 발생한다.
        // 때문에 else로 구분하는 것이 좋음.
        if(!loginUser.getUsername().equals(requestBody.get("username"))){
            // 같은지 비교하여 로그인 확인하기;
            ResponseEntity.builder()
                    .status(403)
                    .body("사용자 정보가 일치하지 않습니다.")
                    .build();
            return;
        }
        else if(!loginUser.getPassword().equals(requestBody.get("password"))){
            ResponseEntity.builder()
                    .status(403)
                    .body("사용자 정보가 일치하지 않습니다.")
                    .build();
        }
        HttpSession session = req.getSession();
        // request 객체가 httpservlet객체라서 session 선언이 가능한데, 일반request 객체에서는 session을 호출할 수 없다.
        session.setAttribute("authentication", loginUser);
        ResponseEntity.builder()
                .status(200)
                .body("로그인 완료!")
                .build();
    }

//    private void errorResponse(HttpServletResponse resp, String message) throws IOException{
//        resp.setStatus(403);            // 인증 실패 권한 없음이란 뜻의 에러 코드
//        resp.setContentType("application/json");
//        Map<String,Object> responseMap = Map.of(
//                "code", 403,
//                "message", message
//        );
//        resp.getWriter().println(JsonParserUtil.stringify(responseMap));
//    }
//
//    private void successResponse(HttpServletResponse resp, String message) throws IOException{
//        resp.setStatus(200);            // 정상 처리 되었다는 뜻
//        resp.setContentType("application/json");
//        Map<String,Object> responseMap = Map.of(
//                "code", 200,
//                "message", message
//        );
//        resp.getWriter().println(JsonParserUtil.stringify(responseMap));
//    }


}
