package com.example.bbs.controller;

import com.example.bbs.entity.Approve;
import com.example.bbs.entity.Information;
import com.example.bbs.service.ApproveService;
import com.example.bbs.utils.Authorization;
import com.example.bbs.utils.JjwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * (TApprove)表控制层
 *
 * @author makejava
 * @since 2019-09-22 14:04:17
 */
@RestController
@RequestMapping("approve")
public class ApproveController {
    /**
     * 服务对象
     */
    @Resource
    private ApproveService approveService;

    /**
     * 根据帖子Id查找点赞总数
     *
     * @param id 帖子id
     * @return 点赞列表
     */
    @GetMapping("select/count/post/{id}")
    public Information<Integer> selectApproveCountByPostId(@PathVariable Integer id) {
        Information<Integer> information=new Information<>();
        Integer result = approveService.selectApproveCountByPostId(id);
        String msg=null;
        Integer status=null;
        if(result<0){
            msg="查询失败";
            status=400;
        }else{
            information.setData(result);
            msg="点赞总数";
            status=200;
        }
        information.setStatus(status);
        information.setMsg(msg);
        return information;
    }

    /**
     * 添加点赞记录
     *
     * @param approve 点赞信息
     * @return 主键值
     */
    @PostMapping("add")
    public Information<Integer> addApprove(Approve approve, HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        String msg="";
        Integer status=202;
        boolean verify = Authorization.verify(request, approve.getUserId());

        if(approve.getPostId()==null || approve.getUserId()==null){
            msg="关键信息不可为空";
            status=406;
        }else if(!verify){
            msg="非法操作";
            status=411;
        }else{
            Integer result = approveService.addApprove(approve);
            if(result>0){
                msg="点赞成功";
                status=200;
                information.setData(result);
            }else if(result==-5){
                msg="目标帖子不存在或用户不存在";
                status=407;
            }else if(result==-2){
                msg="重复点赞";
                status=402;
            }else{
                msg="失败";
                status=400;
            }
        }
        information.setStatus(status);
        information.setMsg(msg);

        return information;
    }

    /**
     * 删除点赞记录
     *
     * @param id 点赞id
     * @return 结果
     */
    @DeleteMapping("delete/{id}")
    public Information<Integer> deleteApprove(@PathVariable Integer id,HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        Approve approve = approveService.selectApproveById(id);
        boolean verify = Authorization.verify(request, approve.getUserId());
        if(id==null){
            information.setMsg("关键信息不可为空");
            information.setStatus(406);
        }else if(!verify){
            information.setMsg("非法操作");
            information.setStatus(411);
        }else{
            Integer result = approveService.deleteApprove(id);
            if(result<0){
                information.setMsg("失败");
                information.setStatus(200);
            }else{
                information.setMsg("取消成功");
                information.setStatus(200);
            }
            information.setData(result);
        }
        return  information;
    }


}