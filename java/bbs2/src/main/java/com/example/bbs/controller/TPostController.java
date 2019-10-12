package com.example.bbs.controller;

import com.example.bbs.entity.TPost;
import com.example.bbs.service.TCollectService;
import com.example.bbs.service.TPostService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TPost)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:00:32
 */
@RestController
@RequestMapping("tPost")
public class TPostController {
    /**
     * 服务对象
     */
    @Resource
    private TPostService tPostService;

    /**
     * 根据分区编号查询帖子
     *
     * @param id    分区编号
     * @param start 距离第一行的偏移量
     * @param num   行数
     * @return 帖子列表
     */
    @GetMapping("selectPostBySectionId")
    public List<TPost> selectPostBySectionId(Integer id, Integer start, Integer num) {
        return tPostService.selectPostBySectionId(id, start, num);
    }

    /**
     * 根据用户id查询帖子
     *
     * @param id    用户id
     * @param start 距离第一行的偏移量
     * @param num   行数
     * @return 帖子列表
     */
    @GetMapping("selectPostByUserId")
    public List<TPost> selectPostByUserId(Integer id, Integer start, Integer num) {
        return tPostService.selectPostByUserId(id, start, num);
    }

    /**
     * 根据类型查找帖子
     *
     * @param type  类型编号，0：图片或文字，1：视频
     * @param start 距离第一行的偏移量
     * @param num   行数
     * @return 帖子列表
     */
    @GetMapping("selectPostByType")
    public List<TPost> selectPostByType(Integer type, Integer start, Integer num) {
        return tPostService.selectPostByType(type, start, num);
    }

    /**
     * 根据帖子id查询
     *
     * @param id 帖子id
     * @return 帖子
     */
    @GetMapping("selectPostById")
    public TPost selectPostById(Integer id) {
        return tPostService.selectPostById(id);
    }

    /**
     * 根据标题模糊查询
     *
     * @param title 标题
     * @param start 距离第一行的偏移量
     * @param num   行数
     * @return 帖子列表
     */
    @GetMapping("selectPostByTitle")
    public List<TPost> selectPostByTile(String title, Integer start, Integer num) {
        return tPostService.selectPostByTitle(title, start, num);
    }

    /**
     * 添加帖子
     *
     * @param tPost 帖子
     * @return 结果
     */
    @GetMapping("addPost")
    public Integer addPost(TPost tPost) {
        return tPostService.addPost(tPost);
    }
    //删除帖子，同时删除收藏

    /**
     * 根据帖子id删除帖子
     *
     * @param id 帖子id
     * @return 结果
     */
    @GetMapping("deletePostById")
    public boolean deletePost(Integer id) {
        return tPostService.deletePostById(id);
    }

    /**
     * 根据用户id删除帖子
     *
     * @param id 用户id
     * @return 结果
     */
    @GetMapping("deletePostByUserId")
    public boolean deletePostByUserId(Integer id) {
        return tPostService.deletePostByUserId(id);
    }

    /**
     * 根据分区id删除帖子
     *
     * @param id 分区id
     * @return 结果
     */
    @GetMapping("deletePostBySectionId")
    public boolean deletePostBySectionId(Integer id) {
        return tPostService.deletePostBySectionId(id);
    }

    /**
     * 根据类型删除
     *
     * @param type 类型
     * @return 结果
     */
    @GetMapping("deletePostByType")
    public boolean deletePostByType(Integer type) {
        return tPostService.deletePostByType(type);
    }

    /**
     * 修改帖子内容
     *
     * @param tPost 帖子
     * @return 结果
     */
    @GetMapping("updatePost")
    public boolean updatePost(TPost tPost) {
        return tPostService.updatePost(tPost);
    }


}