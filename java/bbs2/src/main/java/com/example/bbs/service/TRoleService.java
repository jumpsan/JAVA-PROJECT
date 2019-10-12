package com.example.bbs.service;

import com.example.bbs.entity.TPlate;
import com.example.bbs.entity.TRole;

import java.util.List;

/**
 * (TRole)表服务接口
 *
 * @author makejava
 * @since 2019-09-20 14:00:56
 */
public interface TRoleService {

    /**
     * 根据板块id查询
     *
     * @param id 板块id
     * @return 版主列表
     */
    List<TRole> selectRoleByPlateId(Integer id);

    /**
     * 根据用户id查询
     *
     * @param id 用户id
     * @return 结果
     */
    List<TRole> selectRoleByUserId(Integer id);

    /**
     * 添加版主
     *
     * @param role 版主信息
     * @return 主键值
     */
    TRole addRole(TRole role);

    /**
     * 根据版主id删除
     *
     * @param id 版主id
     * @return 结果
     */
    Integer deleteRoleById(Integer id);

    /**
     * 根据板块id删除
     *
     * @param id 板块id
     * @return 结果
     */
    Integer deleteRoleByPlateId(Integer id);

    /**
     * 根据用户id删除
     *
     * @param id 用户id
     * @return 结果
     */
    Integer deleteRoleByUserId(Integer id);

    /**
     * 根据用户id和版块id
     *
     * @param user_id  用户id
     * @param plate_id 版块id
     * @return 结果
     */
    TRole selectRoleByUserIdAndPlateId(Integer user_id, Integer plate_id);
}