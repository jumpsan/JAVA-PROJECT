package com.example.bbs.service.impl;

import com.example.bbs.entity.TMessage;
import com.example.bbs.dao.TMessageDao;
import com.example.bbs.service.TMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TMessage)表服务实现类
 *
 * @author makejava
 * @since 2019-09-20 13:59:51
 */
@Service("tMessageService")
public class TMessageServiceImpl implements TMessageService {
    @Resource
    private TMessageDao tMessageDao;

    /**
     * 查看消息内容
     *
     * @param receive_id 接收者
     * @param send_id    发送者
     * @return 消息列表
     */
    @Override
    public List<TMessage> selectContent(Integer receive_id, Integer send_id, Integer start, Integer num) {
        return tMessageDao.selectContent(receive_id, send_id, start, num);
    }

    /**
     * 添加消息
     *
     * @param tMessage
     * @return
     */
    @Override
    public Integer addMessage(TMessage tMessage) {
        tMessage = tMessageDao.addMessage(tMessage);
        return tMessage.getId();
    }

}