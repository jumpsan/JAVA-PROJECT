package com.example.bbs.service;

import com.example.bbs.entity.TBlacklist;

import java.util.List;

/**
 * (TBlacklist)表服务接口
 *
 * @author makejava
 * @since 2019-09-22 14:20:30
 */
public interface TBlacklistService {


    /**
     * 根据用户id查询
     *
     * @param id 用户id
     * @return 黑名单列表
     */
    List<TBlacklist> selectListUserId(Integer id);

    /**
     * 添加黑名单
     *
     * @param blacklist 信息
     * @return 主键值
     */
    TBlacklist addBlackList(TBlacklist blacklist);

    /**
     * 删除黑名单
     *
     * @param id 黑名单编号
     * @return 结果
     */
    boolean deleteBlackListById(Integer id);
}