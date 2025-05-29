package com.atguigu.securitydemo.Service;

import com.atguigu.securitydemo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;



public interface UserService extends IService<User> {

    void saveUserDetails(User user);
}
