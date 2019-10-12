package com.example.bbs.service.impl;

import com.example.bbs.entity.TPlate;
import com.example.bbs.dao.TPlateDao;
import com.example.bbs.service.TPlateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TPlate)表服务实现类
 *
 * @author makejava
 * @since 2019-09-20 14:00:18
 */
@Service("tPlateService")
public class TPlateServiceImpl implements TPlateService {
    @Resource
    private TPlateDao tPlateDao;

    /**
     * 查询所有版块
     *
     * @return
     */
    @Override
    public List<TPlate> selectAllPlate() {
        return tPlateDao.selectAllPlate();
    }

    /**
     * 添加版块
     *
     * @param tPlate
     * @return
     */
    @Override
    public Integer addPlate(TPlate tPlate) {
        tPlate = tPlateDao.addPlate(tPlate);
        return tPlate.getId();
    }

    /**
     * 删除版块
     *
     * @param id
     * @return
     */
    @Override
    public boolean deletePlate(Integer id) {
        return tPlateDao.deletePlate(id) > 0;
    }

    /**
     * 修改版块
     *
     * @param tPlate
     * @return
     */
    @Override
    public boolean updatePlate(TPlate tPlate) {
        return tPlateDao.updatePlate(tPlate) > 0;
    }
}