package com.example.bbs.dao;

import com.example.bbs.entity.TSection;

import java.util.List;

/**
 * (TSection)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-20 14:01:07
 */
public interface TSectionDao {
    List<TSection> selectSectionByPlateId(Integer id);

    TSection addSection(TSection section);

    Integer deleteSectionById(Integer id);

    Integer updateSection(TSection section);
}