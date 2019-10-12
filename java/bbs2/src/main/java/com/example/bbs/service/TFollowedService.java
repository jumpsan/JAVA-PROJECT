package com.example.bbs.service;

import com.example.bbs.entity.TFollowed;
import com.example.bbs.entity.TUser;

import java.util.List;

/**
 * (TFollowed)表服务接口
 *
 * @author makejava
 * @since 2019-09-20 13:59:33
 */
public interface TFollowedService {
    List<TUser> selectByFollowId(Integer follow_id, Integer start, Integer num);

    List<TUser> selectByFollowedId(Integer followed_id, Integer start, Integer num);

    Integer addFollow(TFollowed tFollowed);

    boolean deleteFollow(TFollowed tFollowed);

    boolean deleteByFollowId(Integer follow_id);

    boolean deleteByFollowedId(Integer followed_id);
}