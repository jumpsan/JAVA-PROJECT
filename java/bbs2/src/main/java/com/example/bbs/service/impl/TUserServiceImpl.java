package com.example.bbs.service.impl;

import com.example.bbs.entity.TUser;
import com.example.bbs.dao.TUserDao;
import com.example.bbs.service.TUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TUser)表服务实现类
 *
 * @author makejava
 * @since 2019-09-20 14:01:19
 */
@Service("tUserService")
public class TUserServiceImpl implements TUserService {
    @Resource
    private TUserDao tUserDao;

    /**
     * 通过账号选择用户
     *
     * @param id 账号
     * @return 用户
     */
    @Override
    public TUser selectUserById(Integer id) {
        return tUserDao.selectUserById(id);
    }

    /**
     * 通过账号和密码查找
     *
     * @param id       账号
     * @param password 密码
     * @return 用户
     */
    @Override
    public TUser selectUserByIdAndPassword(Integer id, String password) {
        return tUserDao.selectUserByIdAndPassword(id, password);
    }

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 账号
     */
    @Override
    public Integer addUser(TUser user) {
        return tUserDao.addUser(user);
    }

    /**
     * 删除用户
     *
     * @param id 用户账号
     * @return 结果
     */
    @Override
    public boolean deleteUserById(Integer id) {
        return tUserDao.deleteUserById(id) > 0;
    }

    /**
     * 修改用户信息
     *
     * @param user 用户需要修改的信息
     * @return 结果
     */
    @Override
    public boolean updateUserById(TUser user) {
        return tUserDao.updateUserById(user) > 0;
    }


}