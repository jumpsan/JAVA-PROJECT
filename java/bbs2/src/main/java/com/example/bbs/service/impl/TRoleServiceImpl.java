package com.example.bbs.service.impl;

import com.example.bbs.entity.TPlate;
import com.example.bbs.entity.TRole;
import com.example.bbs.dao.TRoleDao;
import com.example.bbs.service.TRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TRole)表服务实现类
 *
 * @author makejava
 * @since 2019-09-20 14:00:56
 */
@Service("tRoleService")
public class TRoleServiceImpl implements TRoleService {
    @Resource
    private TRoleDao tRoleDao;

    /**
     * 根据板块id查询
     *
     * @param id 板块id
     * @return 版主列表
     */
    @Override
    public List<TRole> selectRoleByPlateId(Integer id) {
        return tRoleDao.selectRoleByPlateId(id);
    }

    /**
     * 根据用户id查询
     *
     * @param id 用户id
     * @return 结果
     */
    @Override
    public List<TRole> selectRoleByUserId(Integer id) {
        return tRoleDao.selectRoleByUserId(id);
    }

    /**
     * 添加版主
     *
     * @param role 版主信息
     * @return 主键值
     */
    @Override
    public TRole addRole(TRole role) {
        return tRoleDao.addRole(role);
    }

    /**
     * 根据版主id删除
     *
     * @param id 版主id
     * @return 结果
     */
    @Override
    public Integer deleteRoleById(Integer id) {
        return tRoleDao.deleteRoleById(id);
    }

    /**
     * 根据板块id删除
     *
     * @param id 板块id
     * @return 结果
     */
    @Override
    public Integer deleteRoleByPlateId(Integer id) {
        return tRoleDao.deleteRoleByPlateId(id);
    }

    /**
     * 根据用户id删除
     *
     * @param id 用户id
     * @return 结果
     */
    @Override
    public Integer deleteRoleByUserId(Integer id) {
        return tRoleDao.deleteRoleByUserId(id);
    }

    /**
     * 根据用户id和版块id
     *
     * @param user_id  用户id
     * @param plate_id 版块id
     * @return 结果
     */
    @Override
    public TRole selectRoleByUserIdAndPlateId(Integer user_id, Integer plate_id) {
        return tRoleDao.selectRoleByUserIdAndPlateId(user_id, plate_id);
    }
}