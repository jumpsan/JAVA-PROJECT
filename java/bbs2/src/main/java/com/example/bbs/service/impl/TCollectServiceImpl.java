package com.example.bbs.service.impl;

import com.example.bbs.entity.TCollect;
import com.example.bbs.dao.TCollectDao;
import com.example.bbs.entity.TPost;
import com.example.bbs.entity.TUser;
import com.example.bbs.service.TCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TCollect)表服务实现类
 *
 * @author makejava
 * @since 2019-09-20 13:48:47
 */
@Service("tCollectService")
public class TCollectServiceImpl implements TCollectService {
    @Resource
    private TCollectDao tCollectDao;

    /**
     * 返回某个用户收藏的帖子
     *
     * @param user_id 用户id
     * @return 收藏列表
     */
    @Override
    public List<TPost> queryByUserId(Integer user_id, Integer start, Integer num) {
        return this.tCollectDao.queryUserId(user_id, start, num);
    }

    /**
     * 返回收藏该帖的用户
     *
     * @param post_id 帖子id
     * @param start   偏移量
     * @param num     行数
     * @return 用户列表
     */
    @Override
    public List<TUser> queryByPostId(Integer post_id, Integer start, Integer num) {
        return this.tCollectDao.queryByPostId(post_id, start, num);
    }

    /**
     * 添加收藏
     *
     * @param tCollect 收藏
     * @return 主键值
     */
    @Override
    public Integer addCollect(TCollect tCollect) {
        tCollect = this.tCollectDao.addCollect(tCollect);
        return tCollect.getId();
    }

    /**
     * 删除收藏
     *
     * @param tCollect 收藏
     * @return 结果
     */
    @Override
    public boolean deleteCollect(TCollect tCollect) {
        return this.tCollectDao.deleteCollect(tCollect) > 0;
    }

    /**
     * 删除某个用户的所有收藏
     *
     * @param user_id 用户id
     * @return 结果
     */
    @Override
    public boolean deleteAllCollectByUserId(Integer user_id) {
        return this.tCollectDao.deleteAllCollectByUserId(user_id) > 0;
    }

    /**
     * 删除某个帖子的所有收藏者
     *
     * @param post_id 帖子id
     * @return 结果
     */
    @Override
    public boolean deleteALLCollectByPostId(Integer post_id) {
        return this.tCollectDao.deleteAllCollectByPostId(post_id) > 0;
    }
}