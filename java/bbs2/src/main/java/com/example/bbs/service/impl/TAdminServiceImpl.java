package com.example.bbs.service.impl;

import com.example.bbs.entity.TAdmin;
import com.example.bbs.dao.TAdminDao;
import com.example.bbs.service.TAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TAdmin)表服务实现类
 *
 * @author makejava
 * @since 2019-09-22 13:37:51
 */
@Service("tAdminService")
public class TAdminServiceImpl implements TAdminService {
    @Resource
    private TAdminDao tAdminDao;


    /**
     * 根据管理员账号和密码查询
     *
     * @param id       账号
     * @param password 密码
     * @return 管理员
     */
    @Override
    public TAdmin selectAdminByIdAndPassword(Integer id, String password) {
        return tAdminDao.selectAdminByIdAndPassword(id, password);
    }

    /**
     * 根据账号查询
     *
     * @param id 账号
     * @return 结果
     */
    @Override
    public TAdmin selectAdminById(Integer id) {
        return tAdminDao.selectAdminById(id);
    }

    /**
     * 添加管理员
     *
     * @param admin 管理员信息
     * @return 账号
     */
    @Override
    public TAdmin addAdmin(TAdmin admin) {
        return tAdminDao.addAdmin(admin);
    }

    /**
     * 根据账号删除管理员
     *
     * @param id 账号
     * @return 结果
     */
    @Override
    public boolean deleteAdminById(Integer id) {
        return tAdminDao.deleteAdminById(id) > 0;
    }
}