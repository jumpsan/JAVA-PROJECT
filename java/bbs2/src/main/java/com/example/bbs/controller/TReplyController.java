package com.example.bbs.controller;

import com.example.bbs.entity.TReply;
import com.example.bbs.service.TReplyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (TReply)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:00:45
 */
@RestController
@RequestMapping("tReply")
public class TReplyController {
    /**
     * 服务对象
     */
    @Resource
    private TReplyService tReplyService;

    /**
     * 根据回复id查出
     *
     * @param id 回复id
     * @return
     */
    @GetMapping("selectReplyById")
    public TReply selectReplyById(Integer id) {
        return tReplyService.selectReplyById(id);
    }

    /**
     * 根据帖子id
     *
     * @param id    帖子id
     * @param start 第一行偏移量
     * @param num   行数
     * @return 回复列表
     */
    @GetMapping("selectReplyByPostId")
    public List<TReply> selectReplyByPostId(Integer id, Integer start, Integer num) {
        return tReplyService.selectReplyByPostId(id, start, num);
    }

    /**
     * 根据用户id
     *
     * @param id    用户Id
     * @param start 第一行偏移量
     * @param num   行数
     * @return 回复列表
     */
    @GetMapping("selectReplyByUserId")
    public List<TReply> selectReplyByUserId(Integer id, Integer start, Integer num) {
        return tReplyService.selectReplyByUserId(id, start, num);
    }

    /**
     * 添加回复
     *
     * @param reply 回复
     * @return 主键值
     */
    @GetMapping("addReply")
    public Integer addReply(TReply reply) {
        return tReplyService.addReply(reply);
    }

    /**
     * 删除回复
     *
     * @param id 回复Id
     * @return 结果
     */
    @GetMapping("deleteReplyById")
    public boolean deleteReplyById(Integer id) {
        return tReplyService.deleteReplyById(id);
    }

    /**
     * 修改回复，需要主键
     *
     * @param reply
     * @return
     */
    @GetMapping("updateReply")
    public boolean updateReply(TReply reply) {
        return tReplyService.updateReply(reply);
    }

}