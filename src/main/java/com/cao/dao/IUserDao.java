package com.cao.dao;

import com.cao.model.User;

public interface IUserDao {
    User selectUser(long id);
    Long insertUser(User user);
    User login(User user);
}
