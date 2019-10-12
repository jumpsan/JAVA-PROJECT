package com.example.bbs.service;

import com.example.bbs.entity.TReply;

import java.util.Date;
import java.util.List;

/**
 * (TReply)表服务接口
 *
 * @author makejava
 * @since 2019-09-20 14:00:45
 */
public interface TReplyService {


    List<TReply> selectReplyByPostId(Integer id, Integer start, Integer num);

    List<TReply> selectReplyByUserId(Integer id, Integer start, Integer num);

    Integer addReply(TReply reply);

    boolean deleteReplyById(Integer id);

    boolean updateReply(TReply reply);

    TReply selectReplyById(Integer id);
}