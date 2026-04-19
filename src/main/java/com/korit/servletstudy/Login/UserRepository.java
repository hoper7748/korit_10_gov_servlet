package com.korit.servletstudy.Login;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private List<User> users;
    private ServletContext context;
    public UserRepository(ServletContext context){
        this.context = context;
        String realPath = context.getRealPath("/WEB-INF/users.json");
        try(FileReader fileReader = new FileReader(realPath)) {
            BufferedReader bufferedReader = new BufferedReader((fileReader));
            StringBuilder stringBuilder = new StringBuilder();
            String line ;
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            users = objectMapper.readValue(stringBuilder.toString(), List.class);
        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    // FileReader로 저장해야함 Write를 하란 말
    public User save(){
        ObjectMapper objectMapper = new ObjectMapper();
        String realPath = context.getRealPath("/WEB-INF/users.json");
        try(FileWriter fileWriter = new FileWriter(realPath)){
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, users);

            System.out.println("데이터 저장 성공: " + realPath);
        }catch (IOException e){
            System.err.println("데이터 저장 중 오류 발생!");
            e.printStackTrace();
        }
        return null;
    }

//    public User findById(int id){
//        System.out.println(users);
//        for(int i = 0; i < users.size(); i++) {
//            if(users.get(i).getId() == id){
//                System.out.println(users.get(i));
//            }
//        }
//        return null;
//    }
    public User findById(int id) {
        for (Object obj : users) {
            // 데이터가 Map으로 들어가 있는지 확인
            if (obj instanceof Map) {
                Map<String, Object> userMap = (Map<String, Object>) obj;
                // Map에서 "id" 키를 꺼내어 비교 (JSON 키값이 "id"라고 가정)
                Object idVal = userMap.get("id");
                if (idVal != null && Integer.parseInt(idVal.toString()) == id) {
                    System.out.println("찾음: " + userMap);
                    // 여기서 필요하다면 Map을 User 객체로 변환하여 리턴해야 함
                    return User.builder()
                            .id((Integer) userMap.get("id"))
                            .username((String) userMap.get("username"))
                            .password((String)userMap.get("password"))
                            .email((String) userMap.get("email"))
                            .role((String) userMap.get("role"))
                            .build();
//                    return null;
                }
            }
        }
        return null;
    }

    public User findByUsername(String username){
        for (Object obj : users) {
            // 데이터가 Map으로 들어가 있는지 확인
            if (obj instanceof Map) {
                Map<String, Object> userMap = (Map<String, Object>) obj;
                // Map에서 "id" 키를 꺼내어 비교 (JSON 키값이 "id"라고 가정)
                Object idVal = userMap.get("username");
                if (idVal != null && idVal.toString().equals(username)) {
                    System.out.println("찾음: " + userMap);
                    // 여기서 필요하다면 Map을 User 객체로 변환하여 리턴해야 함
                    return User.builder()
                            .id((Integer) userMap.get("id"))
                            .username((String) userMap.get("username"))
                            .password((String)userMap.get("password"))
                            .email((String) userMap.get("email"))
                            .role((String) userMap.get("role"))
                            .build();
//                    return null;
                }
            }
        }
        return null;
    }

    public List<User> findByAll(){

        return users;
    }


}
