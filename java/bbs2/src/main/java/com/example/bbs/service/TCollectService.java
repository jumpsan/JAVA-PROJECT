package com.example.bbs.service;

import com.example.bbs.entity.TCollect;
import com.example.bbs.entity.TPost;
import com.example.bbs.entity.TUser;

import java.util.List;

/**
 * (TCollect)表服务接口
 *
 * @author makejava
 * @since 2019-09-20 13:48:47
 */
public interface TCollectService {

    List<TPost> queryByUserId(Integer user_id, Integer start, Integer num);

    List<TUser> queryByPostId(Integer post_id, Integer start, Integer num);

    Integer addCollect(TCollect tCollect);

    boolean deleteCollect(TCollect tCollect);

    boolean deleteAllCollectByUserId(Integer user_id);

    boolean deleteALLCollectByPostId(Integer post_id);
}