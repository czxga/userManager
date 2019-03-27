package com.cao.service.impl;

import com.cao.dao.IUserDao;
import com.cao.model.User;
import com.cao.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements IUserService{

    @Resource
    private IUserDao userDao;

    @Override
    public User selectUser(long userId) {
        User user = this.userDao.selectUser(userId);
        if (null != user){
            user.setPassword("******");
        }
        return user;
    }

    @Override
    public Long insertUser(User user) {
        return this.userDao.insertUser(user);
    }

    @Override
    public Boolean login(User user) {
        if (null == user || "".equals(user.getUsername()) || "".equals(user.getPassword())){
            return false;
        }
        User isExistUser = this.userDao.login(user);
        if (null == isExistUser)return false;
        else return true;
    }
}
