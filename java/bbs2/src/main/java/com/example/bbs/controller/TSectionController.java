package com.example.bbs.controller;

import com.example.bbs.entity.TSection;
import com.example.bbs.service.TSectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TSection)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:01:07
 */
@RestController
@RequestMapping("tSection")
public class TSectionController {
    /**
     * 服务对象
     */
    @Resource
    private TSectionService tSectionService;

    /**
     * 查询某个板块下的分区
     *
     * @param id 板块编号
     * @return 分区列表
     */
    @GetMapping("selectSectionByPlateId")
    public List<TSection> selectSectionByPlateId(Integer id) {
        return tSectionService.selectSectionByPlateId(id);
    }

    /**
     * 添加分区
     *
     * @param section 分区
     * @return 主键值，分区编号
     */
    @GetMapping("addSection")
    public Integer addSection(TSection section) {
        return tSectionService.addSection(section);
    }

    /**
     * 根据分区编号删除分区
     *
     * @param id 分区编号
     * @return 结果
     */
    @GetMapping("deleteSectionById")
    public boolean deleteSectionById(Integer id) {
        return tSectionService.deleteSectionById(id);
    }

    /**
     * 修改分区
     *
     * @param section 分区
     * @return 结果
     */
    @GetMapping("updateSection")
    public boolean updateSection(TSection section) {
        return tSectionService.updateSection(section);
    }
}