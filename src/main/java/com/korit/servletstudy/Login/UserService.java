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
        return repository.findById(id);
    }

    public User getFindByUsername(String username){
        return repository.findByUsername(username);
    }

    public void save() {
        System.out.println("저장 시작");
        repository.save();
        System.out.println("저장 완료");
    }
}
