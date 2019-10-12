package com.example.bbs.service.impl;

import com.example.bbs.entity.TSection;
import com.example.bbs.dao.TSectionDao;
import com.example.bbs.service.TSectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TSection)表服务实现类
 *
 * @author makejava
 * @since 2019-09-20 14:01:07
 */
@Service("tSectionService")
public class TSectionServiceImpl implements TSectionService {
    @Resource
    private TSectionDao tSectionDao;

    /**
     * 根据板块id查询
     *
     * @param id 板块id
     * @return 分区列表
     */
    @Override
    public List<TSection> selectSectionByPlateId(Integer id) {
        return tSectionDao.selectSectionByPlateId(id);
    }

    /**
     * 添加分区
     *
     * @param section 分区
     * @return 主键值
     */
    @Override
    public Integer addSection(TSection section) {
        return tSectionDao.addSection(section).getId();
    }

    /**
     * 删除分区
     *
     * @param id 分区编号
     * @return 结果
     */
    @Override
    public boolean deleteSectionById(Integer id) {
        return tSectionDao.deleteSectionById(id) > 0;
    }

    /**
     * 根据主键更新分区
     *
     * @param section 分区
     * @return 结果
     */
    @Override
    public boolean updateSection(TSection section) {
        return tSectionDao.updateSection(section) > 0;
    }
}