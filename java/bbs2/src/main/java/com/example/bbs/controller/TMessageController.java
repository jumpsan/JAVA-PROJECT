package com.example.bbs.controller;

import com.example.bbs.entity.TMessage;
import com.example.bbs.service.TMessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TMessage)表控制层
 *
 * @author makejava
 * @since 2019-09-20 13:59:51
 */
@RestController
@RequestMapping("tMessage")
public class TMessageController {
    /**
     * 服务对象
     */
    @Resource
    private TMessageService tMessageService;

    /**
     * @param receive_id 接收人
     * @param send_id    发送人
     * @return 返回信息列表
     */
    @GetMapping("selectContent")
    public List<TMessage> selectContent(Integer receive_id, Integer send_id, Integer start, Integer num) {
        return tMessageService.selectContent(receive_id, send_id, start, num);
    }

    /**
     * 添加信息
     *
     * @param tMessage 信息
     * @return 主键值
     */
    @PostMapping("addMessage")
    public Integer addMessage(TMessage tMessage) {
        return tMessageService.addMessage(tMessage);
    }

}