package com.example.bbs.controller;

import com.example.bbs.entity.Information;
import com.example.bbs.entity.Message;
import com.example.bbs.entity.Page;
import com.example.bbs.service.MessageService;
import com.example.bbs.utils.Authorization;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (TMessage)表控制层
 *
 * @author makejava
 * @since 2019-09-20 13:59:51
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;

    /**
     * @param receiveId 接收人
     * @param sendId    发送人
     * @return 返回信息列表
     */
    @GetMapping("content/{sendId}/{receiveId}/{page}/{size}")
    public Information<Page> selectContent(@PathVariable Integer receiveId, @PathVariable Integer sendId, @PathVariable Integer page, @PathVariable Integer size) {
        Information<Page> information=new Information<>();
        if(receiveId==null || sendId==null || page==null || size==null){
            information.setMsg("关键信息不可为空");
            information.setStatus(406);
        }else{
            Page<Message> messagePage=new Page<>();
            Integer start=(page-1)*size;
            List<Message> messages = messageService.selectContent(receiveId, sendId, start, size);
            messagePage.setDatas(messages);
            Integer totalPage = messageService.selectAllCountByTalkers(receiveId, sendId)/size+1;
            messagePage.setTotalPage(totalPage);
            information.setStatus(200);
            information.setMsg("分页");
            information.setData(messagePage);
        }

        return information;
    }

    /**
     * 添加信息
     *
     * @param message 信息
     * @return 主键值
     */
    @PostMapping("add")
    public Information<Integer> addMessage(Message message, HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        boolean verify = Authorization.verify(request, message.getSendId());
        if(message.getReceiveId()==null || message.getSendId()==null || message.getContent()==null){
            information.setMsg("关键信息不可为空");
            information.setStatus(406);
        }else if(!verify){
            information.setMsg("非法操作");
            information.setStatus(411);
        }else{
            Integer result = messageService.addMessage(message);
            if(result==-7){
                information.setStatus(400);
                information.setMsg("添加失败");
            }else{
                information.setMsg("主键");
                information.setStatus(200);
                information.setData(result);
            }
        }
        return information;
    }

}