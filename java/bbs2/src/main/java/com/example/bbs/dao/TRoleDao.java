package com.example.bbs.dao;

import com.example.bbs.entity.TRole;
import java.util.List;

/**
 * (TRole)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-20 14:00:56
 */
public interface TRoleDao {
    List<TRole> selectRoleByPlateId(Integer id);

    TRole selectRoleByUserIdAndPlateId(Integer user_id, Integer plate_id);

    List<TRole> selectRoleByUserId(Integer id);

    TRole addRole(TRole role);

    Integer deleteRoleById(Integer id);

    Integer deleteRoleByPlateId(Integer id);

    Integer deleteRoleByUserId(Integer id);
}