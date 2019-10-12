package com.example.bbs.dao;

import com.example.bbs.entity.TCollect;
import com.example.bbs.entity.TPost;
import com.example.bbs.entity.TUser;


import java.util.List;

/**
 * (TCollect)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-20 13:48:47
 */
public interface TCollectDao {

    List<TPost> queryUserId(Integer user_id, Integer start, Integer num);

    List<TUser> queryByPostId(Integer post_id, Integer start, Integer num);

    TCollect addCollect(TCollect tCollect);

    Integer deleteCollect(TCollect tCollect);

    Integer deleteAllCollectByUserId(Integer user_id);

    Integer deleteAllCollectByPostId(Integer post_id);
}