package com.atguigu.securitydemo.config;

import com.atguigu.securitydemo.entity.User;
import com.atguigu.securitydemo.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    /**
     * 向数据库插入新的用户信息
     * @param userDetails
     */
    @Override
    public void createUser(UserDetails userDetails) {

        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(true);
        userMapper.insert(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    /**
     * 从数据库获取用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<User>();//mybatisplus的查询对象,装载各种查询条件
        userQueryWrapper.eq("username", username);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {

//            构建security的user对象
            return new org.springframework.security.core.userdetails.User(
                    //这里是实体类的user
                    user.getUsername(),
                    user.getPassword(),
                    user.getEnabled(),
                    //下面四个参数我们的User里面没有，我们的程序很简单，我们的User里面只有3个参数
                    true,//账号是否过期
                    true,//凭证是否过期
                    true,//是否未被锁定
                    new ArrayList<>() //角色列表（授权
            );



        }

    }
}
