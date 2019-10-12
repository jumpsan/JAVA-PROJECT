package com.example.bbs.controller;

import com.example.bbs.entity.*;
import com.example.bbs.service.ReplyService;
import com.example.bbs.utils.Authorization;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (TReply)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:00:45
 */
@RestController
public class ReplyController {
    /**
     * 服务对象
     */
    @Resource
    private ReplyService replyService;

    /**
     * 根据回复id查出
     *
     * @param id 回复id
     * @return
     */
    @GetMapping("reply/select/{id}")
    public Information selectReplyById(@PathVariable Integer id) {
        Information<Reply> information=new Information<>();
        if(id==null){
            information.setMsg("回复id不能为空");
            information.setStatus(406);
        }else{
            Reply reply = replyService.selectReplyById(id);
            if(reply!=null) {
                information.setData(reply);
                information.setStatus(200);
                information.setMsg("回复");
            }else {
                information.setMsg("无");
                information.setStatus(204);
            }
        }
        return information;
    }

    /**
     * 根据帖子id
     *
     * @param id    帖子id
     * @param page 第一行偏移量
     * @param size   行数
     * @return 回复列表
     */
    @GetMapping("reply/select/post/{id}/{page}/{size}")
    public Information<Page> selectReplyByPostId(@PathVariable Integer id, @PathVariable Integer page,@PathVariable Integer size) {
        Information<Page> information =new Information<>();
        if(id==null){
            information.setMsg("帖子id不能为空");
            information.setStatus(406);
        }else{
            //总页数
            Integer totalPage=replyService.selectAllReplyCountByPostId(id)/page+1;
            Integer start=(page-1)*page;
            List<Reply> replies= replyService.selectReplyByPostId(id,start, size);
            Page<Reply> pageObject=new Page<>();
            pageObject.setDatas(replies);
            pageObject.setTotalPage(totalPage);
            if(replies!=null) {
                information.setData(pageObject);
                information.setMsg("回复列表");
                information.setStatus(200);
            }else {
                information.setMsg("无");
                information.setStatus(204);
            }
        }
        return information;
    }

    /**
     * 根据用户id查询
     *
     * @param id    用户Id
     * @param page 第一行偏移量
     * @param size   行数
     * @return 回复列表
     */
    @GetMapping("reply/select/user/{id}/{page}/{size}")
    public Information<Page> selectReplyByUserId(@PathVariable Integer id, @PathVariable Integer page,@PathVariable Integer size) {
        Information<Page> information =new Information<>();
        if(id==null){
            information.setMsg("用户id不能为空");
            information.setStatus(406);
        }else{
            //总页数
            Integer totalPage=replyService.selectAllReplyCountByUserId(id)/size+1;

            Integer start=(page-1)*size;
            List<Reply> replies= replyService.selectReplyByUserId(id,start, size);
            Page<Reply> pageObject=new Page<>();
            pageObject.setDatas(replies);
            pageObject.setTotalPage(totalPage);
            if(replies!=null) {
                information.setData(pageObject);
                information.setMsg("回复列表");
                information.setStatus(200);
            }else {
                information.setMsg("无");
                information.setStatus(204);
            }
        }
        return information;
    }

    /**
     * 添加回复
     * id user_id post_id content
     * @param reply 回复
     * @return 主键值
     */
    @PostMapping("reply/add")
    public Information addReply(Reply reply, HttpServletRequest request) {
        Information<Integer> information =new Information<>();
        String msg="";
        Integer status=202;
        boolean verify = Authorization.verify(request, reply.getUserId());
        if (reply.getUserId()==null && reply.getContent()!=null && reply.getPostId()==null && reply.getContent().trim().equals("")) {
            msg = "必需信息为空";
            status=406;
        }else if(!verify){
            msg="非法操作";
            status=411;
        }else{
            Integer replyId = replyService.addReply(reply);
            information.setData(replyId);
            if (replyId==-3) {
                msg = "创建用户不存在";
                status=404;
            }
            else if (replyId==-5) {
                msg = "所添加目标帖子不存在";
                status=407;
            }else if(replyId==-6){
                msg="用户在黑名单，无权回帖";
                status=401;
            }
            else if(replyId==0){
                msg="添加失败";
                status=400;
            }
            else {
                msg = "创建成功";
                status=200;
            }
        }
        information.setMsg(msg);
        information.setStatus(status);
        return information;
    }

    /**
     * 删除回复
     *
     * @param id 回复Id
     * @return 结果
     */
    @DeleteMapping("reply/delete/{id}")
    public Information deleteReplyById(@PathVariable Integer id,HttpServletRequest request) {
        Information<Integer> information =new Information<>();
        String msg="";
        Integer status=202;
        Reply reply = replyService.selectReplyById(id);
        if(reply==null){
            msg="回复不存在";
            status=407;
        }else{
            boolean verify = Authorization.verify(request, reply.getUserId());
            if(verify){
                Integer result = replyService.deleteReplyById(id);
                if(result>0){
                    msg="删除成功";
                    status=200;
                    information.setData(result);
                }
                else {
                    msg = "删除失败";
                    status=400;
                }
            }else{
                msg="非法操作";
                status=411;
            }
        }
        information.setStatus(status);
        information.setMsg(msg);
        return information;
    }

    /**
     * 修改回复，需要主键
     * id user_id post_id content
     * @param reply
     * @return
     */
    @PutMapping("reply/update")
    public Information updateReply(Reply reply,HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        Reply checkReply = replyService.selectReplyById(reply.getId());
        boolean verify = Authorization.verify(request, checkReply.getUserId());
        if(reply.getId()==null){
            information.setMsg("回复id不能为空");
            information.setStatus(403);
        }else if(!verify){
            information.setStatus(411);
            information.setMsg("非法操作");
        }else {
            Integer re=replyService.updateReply(reply);
            if(re==null || re==0){
                information.setMsg("更新失败");
                information.setStatus(400);
            } else{
                information.setMsg("更新成功");
                information.setStatus(200);
            }
            information.setData(re);
        }
        return information;
    }

}