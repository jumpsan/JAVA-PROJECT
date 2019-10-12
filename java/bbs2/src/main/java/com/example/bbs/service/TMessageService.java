package com.example.bbs.service;

import com.example.bbs.entity.TMessage;

import java.util.List;

/**
 * Integer
 * (TMessage)表服务接口
 *
 * @author makejava
 * @since 2019-09-20 13:59:51
 */
public interface TMessageService {


    List<TMessage> selectContent(Integer receive_id, Integer send_id, Integer start, Integer num);

    Integer addMessage(TMessage tMessage);
}