package com.korit.servletstudy.Login;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public void Save(){
        repository.save();
    }

    public List<User> getAll() {


        return repository.findByAll();
    }

    public User getFindById(int id){
        System.out.println("ss");
        return repository.findById(id);
    }

    public User getFindByUsername(String username){
        System.out.println("dd");
        return repository.findByUsername(username);
    }
}
