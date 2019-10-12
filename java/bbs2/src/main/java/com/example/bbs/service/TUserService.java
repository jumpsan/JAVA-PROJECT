package com.example.bbs.service;

import com.example.bbs.entity.TUser;

/**
 * (TUser)表服务接口
 *
 * @author makejava
 * @since 2019-09-20 14:01:19
 */
public interface TUserService {


    TUser selectUserById(Integer id);

    TUser selectUserByIdAndPassword(Integer id, String password);

    Integer addUser(TUser user);

    boolean deleteUserById(Integer id);

    boolean updateUserById(TUser user);
}