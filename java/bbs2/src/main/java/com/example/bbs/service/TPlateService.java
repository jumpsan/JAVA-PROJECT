package com.example.bbs.service;

import com.example.bbs.entity.TPlate;

import java.util.List;

/**
 * (TPlate)表服务接口
 *
 * @author makejava
 * @since 2019-09-20 14:00:18
 */
public interface TPlateService {

    List<TPlate> selectAllPlate();

    Integer addPlate(TPlate tPlate);

    boolean deletePlate(Integer id);

    boolean updatePlate(TPlate tPlate);
}