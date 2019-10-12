package com.example.bbs.dao;

import com.example.bbs.entity.TReply;


import java.util.List;

/**
 * (TReply)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-20 14:00:45
 */
public interface TReplyDao {


    List<TReply> selectReplyByPostId(Integer id, Integer start, Integer num);

    List<TReply> selectReplyByUserId(Integer id, Integer start, Integer num);

    TReply addReply(TReply reply);

    Integer deleteReplyById(Integer id);

    Integer updateReply(TReply reply);

    TReply selectReplyById(Integer id);
}