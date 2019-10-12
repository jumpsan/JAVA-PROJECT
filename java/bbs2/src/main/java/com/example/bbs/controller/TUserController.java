package com.example.bbs.controller;

import com.example.bbs.entity.TUser;
import com.example.bbs.service.TUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TUser)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:01:19
 */
@RestController
@RequestMapping("tUser")
public class TUserController {
    /**
     * 服务对象
     */
    @Resource
    private TUserService tUserService;

    /**
     * 根据账号查询
     *
     * @param id
     * @return
     */
    @PostMapping("selectUserById")
    public TUser selectUserById(Integer id) {
        return tUserService.selectUserById(id);
    }

    /**
     * 根据账号密码查询
     *
     * @param id       账号
     * @param password 密码
     * @return 用户
     */
    @PostMapping("selectUserByIdAndPassword")
    public TUser selectUserByIdAndPassword(Integer id, String password) {
        return tUserService.selectUserByIdAndPassword(id, password);
    }

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 账号
     */
    @PostMapping("addUser")
    public Integer addUser(TUser user) {
        return tUserService.addUser(user);
    }

    /**
     * 根据账号删除用户
     *
     * @param id 账号
     * @return 结果
     */
    @GetMapping("deleteUser")
    public boolean deleteUser(Integer id) {
        return tUserService.deleteUserById(id);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户
     * @return 结果
     */
    @PostMapping("updateUser")
    public boolean updateUser(TUser user) {
        return tUserService.updateUserById(user);
    }


}