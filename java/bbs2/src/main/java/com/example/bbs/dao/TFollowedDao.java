package com.example.bbs.dao;

import com.example.bbs.entity.TFollowed;
import com.example.bbs.entity.TUser;

import java.util.List;

/**
 * (TFollowed)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-20 13:59:32
 */
public interface TFollowedDao {

    List<TUser> selectByFollowId(Integer follow_id, Integer start, Integer num);

    List<TUser> selectByFollowedId(Integer followed_id, Integer start, Integer num);

    TFollowed addFollow(TFollowed tFollowed);

    Integer deleteFollow(TFollowed tFollowed);

    Integer deleteByFollowId(Integer follow_id);

    Integer deleteByFollowedId(Integer followed_id);
}