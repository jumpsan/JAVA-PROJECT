package com.example.bbs.service.impl;

import com.example.bbs.entity.TBlacklist;
import com.example.bbs.dao.TBlacklistDao;
import com.example.bbs.service.TBlacklistService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TBlacklist)表服务实现类
 *
 * @author makejava
 * @since 2019-09-22 14:20:30
 */
@Service("tBlacklistService")
public class TBlacklistServiceImpl implements TBlacklistService {
    @Resource
    private TBlacklistDao tBlacklistDao;

    /**
     * 根据用户id查询
     *
     * @param id 用户id
     * @return 黑名单列表
     */
    @Override
    public List<TBlacklist> selectListUserId(Integer id) {
        return tBlacklistDao.selectListUserId(id);
    }

    /**
     * 添加黑名单
     *
     * @param blacklist 信息
     * @return 主键值
     */
    @Override
    public TBlacklist addBlackList(TBlacklist blacklist) {
        return tBlacklistDao.addBlackList(blacklist);
    }

    /**
     * 删除黑名单
     *
     * @param id 黑名单编号
     * @return 结果
     */
    @Override
    public boolean deleteBlackListById(Integer id) {
        return tBlacklistDao.deleteBlackListById(id) > 0;
    }
}