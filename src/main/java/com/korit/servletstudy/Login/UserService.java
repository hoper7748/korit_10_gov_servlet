package com.korit.servletstudy.Login;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
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
        return repository.findById2(id);
    }

    public User getFindByUsername(String username){
        return repository.findByUsername2(username);
    }

    public void save() {
        System.out.println("저장 시작");
//        repository.save2(User.builder().id("busan_kim").email());
        System.out.println("저장 완료");
    }
}
