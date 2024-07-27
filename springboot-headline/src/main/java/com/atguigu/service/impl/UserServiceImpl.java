package com.atguigu.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.MD5Util;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author 李文
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-03-19 19:36:32
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser=userMapper.selectOne(userLambdaQueryWrapper);
        if(loginUser==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if(!StringUtils.isEmpty(user.getUserPwd())&& MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map data=new HashMap();
            data.put("token",token);
            return Result.ok(data);
        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        boolean expiration = jwtHelper.isExpiration(token);
        if (expiration) {
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        int userId = Math.toIntExact(jwtHelper.getUserId(token));
        User user = userMapper.selectById(userId);
        user.setUserPwd("");
        Map data=new HashMap();
        data.put("loginUser",user);
        return Result.ok(data);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,username);
        Long l = userMapper.selectCount(userLambdaQueryWrapper);
        if(l==0){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    @Override
    public Result regist(User user) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        Long l = userMapper.selectCount(userLambdaQueryWrapper);
        if(l>0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));

        userMapper.insert(user);
        return Result.ok(null);
    }
}




