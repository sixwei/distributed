package com.neuedu.service;

import com.neuedu.pojo.User;

import java.util.List;

public interface UserService {

    public List<User> listUser();

    public int deleteUser(Integer id);




}
