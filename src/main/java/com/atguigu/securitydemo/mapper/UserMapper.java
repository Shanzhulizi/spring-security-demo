package com.atguigu.securitydemo.mapper;

import com.atguigu.securitydemo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
//    通用mapper，需要泛型
//    通用mapper已经定义了大量基础的方法


}
