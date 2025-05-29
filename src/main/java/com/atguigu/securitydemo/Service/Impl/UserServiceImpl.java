package com.atguigu.securitydemo.Service.Impl;

import com.atguigu.securitydemo.Service.UserService;

import com.atguigu.securitydemo.config.DBUserDetailsManager;
import com.atguigu.securitydemo.entity.User;
import com.atguigu.securitydemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.sql.RowSet;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private DBUserDetailsManager dbUserDetailsManager;

    @Override
    public void saveUserDetails(User user) {

        UserDetails userDetails= org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()
                .username(user.getUsername())
                .password(user.getPassword())
//                .roles("USER")
                .build();
        dbUserDetailsManager.createUser(userDetails);
    }
}
