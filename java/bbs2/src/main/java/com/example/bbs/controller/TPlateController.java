package com.example.bbs.controller;

import com.example.bbs.entity.TPlate;
import com.example.bbs.service.TPlateService;
import com.example.bbs.service.TSectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TPlate)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:00:18
 */
@RestController
@RequestMapping("tPlate")
public class TPlateController {
    /**
     * 服务对象
     */
    @Resource
    private TPlateService tPlateService;

    /**
     * 查询所有版块
     *
     * @return 板块列表
     */
    @GetMapping("selectAllPlate")
    public List<TPlate> selectAllPlate() {
        return tPlateService.selectAllPlate();
    }

    /**
     * 添加板块
     *
     * @param tPlate 板块信息
     * @return 结果
     */
    @GetMapping("addPlate")
    public Integer addPlate(TPlate tPlate) {
        return tPlateService.addPlate(tPlate);
    }

    /**
     * 删除板块
     *
     * @param id 板块id
     * @return 结果
     */
    @GetMapping("deletePlate")
    public boolean deletePlate(Integer id) {
        return tPlateService.deletePlate(id);
    }

    @GetMapping("updatePlate")
    public boolean updatePlate(TPlate tPlate) {
        return tPlateService.updatePlate(tPlate);
    }
}