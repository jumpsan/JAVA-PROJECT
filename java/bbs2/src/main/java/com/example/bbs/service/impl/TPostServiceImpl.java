package com.example.bbs.service.impl;

import com.example.bbs.entity.TPost;
import com.example.bbs.dao.TPostDao;
import com.example.bbs.service.TPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TPost)表服务实现类
 *
 * @author makejava
 * @since 2019-09-20 14:00:31
 */
@Service("tPostService")
public class TPostServiceImpl implements TPostService {
    @Resource
    private TPostDao tPostDao;

    /**
     * 根据帖子id查询
     *
     * @param id 帖子id
     * @return 帖子
     */
    @Override
    public TPost selectPostById(Integer id) {
        return tPostDao.selectPostById(id);
    }

    /**
     * 添加帖子
     *
     * @param tPost
     * @return
     */
    @Override
    public Integer addPost(TPost tPost) {
        tPost = tPostDao.addPost(tPost);
        return tPost.getId();
    }

    /**
     * 根据帖子id删除帖子
     *
     * @param id 帖子id
     * @return 结果
     */
    @Override
    public boolean deletePostById(Integer id) {
        return tPostDao.deletePostById(id) > 0;
    }

    /**
     * 根据用户Id删除
     *
     * @param id 用户id
     * @return 结果
     */
    @Override
    public boolean deletePostByUserId(Integer id) {
        return tPostDao.deletePostByUserId(id) > 0;
    }

    /**
     * 根据分区id删除
     *
     * @param id 分区Id
     * @return 结果
     */
    @Override
    public boolean deletePostBySectionId(Integer id) {
        return tPostDao.deletePostBySectionId(id) > 0;
    }

    /**
     * 根据类型删除
     *
     * @param type 类型编号
     * @return 结果
     */
    @Override
    public boolean deletePostByType(Integer type) {
        return tPostDao.deletePostByType(type) > 0;
    }

    /**
     * 更新帖子,必须传入主键
     *
     * @param tPost 帖子
     * @return 结果
     */
    @Override
    public boolean updatePost(TPost tPost) {
        return tPostDao.updatePost(tPost) > 0;
    }

    /**
     * 根据分区选择帖子
     *
     * @param id    板块id
     * @param start 偏移量
     * @param num   行数
     * @return 结果
     */
    @Override
    public List<TPost> selectPostBySectionId(Integer id, Integer start, Integer num) {
        return tPostDao.selectPostBySectionId(id, start, num);
    }

    /**
     * 根据用户Id查询
     *
     * @param id    用户id
     * @param start 偏移
     * @param num   行数
     * @return 结果
     */
    @Override
    public List<TPost> selectPostByUserId(Integer id, Integer start, Integer num) {
        return tPostDao.selectPostByUserId(id, start, num);
    }

    /**
     * 根据类型查询帖子
     *
     * @param type  类型编号
     * @param start 偏移
     * @param num   行数
     * @return 结果
     */
    @Override
    public List<TPost> selectPostByType(Integer type, Integer start, Integer num) {
        return tPostDao.selectPostByType(type, start, num);
    }

    /**
     * 根据标题模糊查询
     *
     * @param title 标题
     * @param start 偏移
     * @param num   行数
     * @return 结果
     */
    @Override
    public List<TPost> selectPostByTitle(String title, Integer start, Integer num) {
        return tPostDao.selectPostByTitle(title, start, num);
    }
}