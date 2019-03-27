package com.cao.service;

import com.cao.model.User;

public interface IUserService {
    /**
     * 根据id搜索用户
     * @author caozhixiang
     * @date 2019/3/27
     * @param userId
     * @return
     */
    public User selectUser(long userId);

    /**
     * 插入新用户，返回自增长的主键值
     * @author caozhixiang
     * @date 2019/3/27
     * @param user
     * @return
     */
    public Long insertUser(User user);

    /**
     *用户登录
     * @author caozhixiang
     * @date 2019/3/27
     * @param user
     * @return
     */
    public Boolean login(User user);
}
