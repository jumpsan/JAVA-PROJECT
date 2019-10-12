package com.example.bbs.dao;

import com.example.bbs.entity.TAdmin;




/**
 * (TAdmin)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-22 13:37:50
 */
public interface TAdminDao {
    /**
     * 根据管理员账号和密码查询
     *
     * @param id       账号
     * @param password 密码
     * @return 管理员
     */
    TAdmin selectAdminByIdAndPassword(Integer id, String password);

    /**
     * 根据账号查询
     *
     * @param id 账号
     * @return 结果
     */
    TAdmin selectAdminById(Integer id);

    /**
     * 添加管理员
     *
     * @param admin 管理员信息
     * @return 账号
     */
    TAdmin addAdmin(TAdmin admin);

    /**
     * 根据账号删除管理员
     *
     * @param id 账号
     * @return 结果
     */
    Integer deleteAdminById(Integer id);
}