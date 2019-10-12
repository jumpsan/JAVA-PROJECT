package com.example.bbs.dao;

import com.example.bbs.entity.TPlate;

import java.util.List;

/**
 * (TPlate)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-20 14:00:18
 */
public interface TPlateDao {
    List<TPlate> selectAllPlate();

    TPlate addPlate(TPlate tPlate);

    Integer deletePlate(Integer id);

    Integer updatePlate(TPlate tPlate);
}