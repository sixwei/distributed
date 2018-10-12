package com.neuedu.mapper;


import com.neuedu.pojo.User;

import java.util.List;

public interface UserMapper {

    public List<User> listUser();

    public int deleteUser(Integer id);

}
