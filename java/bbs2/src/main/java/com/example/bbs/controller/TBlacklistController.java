package com.example.bbs.controller;

import com.example.bbs.entity.TBlacklist;
import com.example.bbs.service.TBlacklistService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TBlacklist)表控制层
 *
 * @author makejava
 * @since 2019-09-22 14:20:30
 */
@RestController
@RequestMapping("tBlacklist")
public class TBlacklistController {
    /**
     * 服务对象
     */
    @Resource
    private TBlacklistService tBlacklistService;

    /**
     * 根据用户id查询
     *
     * @param id 用户id
     * @return 黑名单列表
     */
    @GetMapping("selectListByUserId")
    public List<TBlacklist> selectListByUserId(Integer id) {
        return tBlacklistService.selectListUserId(id);
    }

    /**
     * 添加黑名单
     *
     * @param blacklist 信息
     * @return 主键值
     */
    @GetMapping("addBlackList")
    public Integer addBlackList(TBlacklist blacklist) {
        blacklist = tBlacklistService.addBlackList(blacklist);
        return blacklist.getId();
    }

    /**
     * 删除黑名单
     *
     * @param id 黑名单编号
     * @return 结果
     */
    @GetMapping("deleteBlackListById")
    public boolean deleteBlcakListById(Integer id) {
        return tBlacklistService.deleteBlackListById(id);
    }

}