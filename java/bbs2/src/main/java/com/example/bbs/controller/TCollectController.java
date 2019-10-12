package com.example.bbs.controller;

import com.example.bbs.entity.TCollect;
import com.example.bbs.entity.TPost;
import com.example.bbs.entity.TUser;
import com.example.bbs.service.TCollectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Generated;
import javax.annotation.Resource;
import java.util.List;

/**
 * (TCollect)表控制层
 *
 * @author makejava
 * @since 2019-09-20 13:48:52
 */
@RestController
@RequestMapping("tCollect")
public class TCollectController {
    /**
     * 服务对象
     */
    @Resource  //按照名称寻找Bean
    private TCollectService tCollectService;

    /**
     * 查询某个用户所收藏的帖子
     *
     * @param user_id 收藏的用户
     * @return 帖子列表
     */
    @GetMapping("selectByUserId")
    public List<TPost> selectByUserId(Integer user_id, Integer start, Integer num) {
        return this.tCollectService.queryByUserId(user_id, start, num);
    }

    /**
     * 查询收藏某个帖子的用户
     *
     * @param post_id 帖子id
     * @return 用户列表
     */
    @GetMapping("selectByPostId")
    public List<TUser> selectByPostId(Integer post_id, Integer start, Integer num) {
        return this.tCollectService.queryByPostId(post_id, start, num);
    }

    /**
     * 添加收藏记录
     *
     * @param tCollect 收藏记录
     * @return 返回主键值
     */
    @GetMapping("addCollect")
    public Integer addCollect(TCollect tCollect) {
        return this.tCollectService.addCollect(tCollect);
    }

    /**
     * 删除单个收藏记录
     *
     * @param tCollect 收藏记录
     * @return 结果
     */
    @GetMapping("deleteCollect")
    public boolean deleteCollect(TCollect tCollect) {
        //需要先对user_id和post_id判断
        return this.tCollectService.deleteCollect(tCollect);
    }

    /**
     * 删除某个用户所有的记录
     *
     * @param user_id 用户id
     * @return 结果
     */
    @GetMapping("deleteAllCollectByUserId")
    public boolean deleteAllCollect(Integer user_id) {
        return this.tCollectService.deleteAllCollectByUserId(user_id);
    }

    /**
     * 当某个帖子被删除后，需要删除所有相关记录
     *
     * @param post_id 帖子id
     * @return 结果
     */
    @GetMapping("deleteAllCollectByPostId")
    public boolean deleteAllCollectByPostId(Integer post_id) {
        return this.tCollectService.deleteALLCollectByPostId(post_id);
    }


}