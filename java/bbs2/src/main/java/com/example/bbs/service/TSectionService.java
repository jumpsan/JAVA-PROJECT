package com.example.bbs.service;

import com.example.bbs.entity.TSection;

import java.util.List;

/**
 * (TSection)表服务接口
 *
 * @author makejava
 * @since 2019-09-20 14:01:07
 */
public interface TSectionService {


    List<TSection> selectSectionByPlateId(Integer id);

    Integer addSection(TSection section);

    boolean deleteSectionById(Integer id);

    boolean updateSection(TSection section);
}