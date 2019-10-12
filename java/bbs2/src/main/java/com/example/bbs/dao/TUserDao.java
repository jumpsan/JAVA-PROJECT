package com.example.bbs.dao;

import com.example.bbs.entity.TUser;


/**
 * (TUser)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-20 14:01:19
 */
public interface TUserDao {


    TUser selectUserById(Integer id);

    TUser selectUserByIdAndPassword(Integer id, String password);

    Integer addUser(TUser user);

    Integer deleteUserById(Integer id);

    Integer updateUserById(TUser user);
}