package com.korit.servletstudy.Login;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users;
    public UserRepository(ServletContext context){

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
            System.out.println(users);
        } catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    // FileReader로 저장해야함 Write를 하란 말
    public User save(){


        return null;
    }

    public User findById(int id){
        System.out.println(users);
        for(User user : users){
            System.out.println(user +" " + id);
            if(user.get() == id){
                return user;
            }
        }
        return null;
    }

    public User findByUsername(String username){
        for(User user : users){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public List<User> findByAll(){

        return users;
    }


}
