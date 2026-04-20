package com.korit.servletstudy.Login;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/users")
public class UserController extends HttpServlet {

    private UserService userService;


    @Override
    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
        UserRepository userRepository = new UserRepository(config.getServletContext());
        userService= new UserService(userRepository);       // 종속성 주입
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("UserController Event");
        List<User> users = userService.getAll(); // 받은 모든 사용자 리스트 응답.
        User user1 = null , user2 = null;
        String p1 = req.getParameter("id");
        String p2 = req.getParameter("username");
        System.out.println(p1);
        System.out.println(p2);
        user1 = userService.getFindById(Integer.parseInt(p1));
        user2 = userService.getFindByUsername(p2);
//        System.out.println(user1);

        ResponseEntity.builder()
                .status(200)
                .body(user1)
                .build()
                .response(resp);
        userService.save();
//
//        ResponseEntity.builder()
//                .status(200)
//                .body(users)
//                .build()
//                .response(resp);
    }
}
