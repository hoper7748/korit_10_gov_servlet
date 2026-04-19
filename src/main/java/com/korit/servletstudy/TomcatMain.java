package com.korit.servletstudy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.servlet.annotation.WebServlet;

@Data
@AllArgsConstructor
@Getter
class Req {
    private String requestUrl;
    private Resp resp;

}

class Resp{

}

@Data
class TestServlet {
    private static TestServlet instance;
    private TestServlet() {}
    public static TestServlet getInstance(){
        if(instance == null) instance = new TestServlet();
        return instance;
    }

    public void Init(){
        System.out.println("객체 생성 및 초기화");
    }

    public void service(Req req, Resp resp){
        System.out.println(req.getRequestUrl());
    }
    public void destroy() {
        System.out.println("소멸");
    }
}

public class TomcatMain {
    public static void main(String[] args) {
        String requestUrl = "https://localhost8080/backserver/first";
        TestServlet testServlet = TestServlet.getInstance();
        testServlet.Init();
        testServlet.service(new Req(requestUrl, new Resp()), new Resp());

    }


}
