package com.korit.servletstudy.Login;

import java.util.List;

public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public List<User> getAll() {
        return null;
    }
}
