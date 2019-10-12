package com.example.bbs.controller;


import com.example.bbs.entity.Follow;
import com.example.bbs.entity.Information;
import com.example.bbs.entity.Page;
import com.example.bbs.entity.User;
import com.example.bbs.service.FollowService;
import com.example.bbs.utils.Authorization;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * (TFollowed)表控制层
 *
 * @author makejava
 * @since 2019-09-20 13:59:33
 */
@RestController
@RequestMapping("follow")  //用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
public class FollowController {
    /**
     * 服务对象
     */
    @Resource
    private FollowService followService;

    /**
     * 查找某个用户所关注的人
     *
     * @param followId 关注人
     * @return 关注列表
     */
    @GetMapping("select/follow/{followId}/{page}/{size}")
    public Information<Page> selectByFollowId(@PathVariable Integer followId, @PathVariable Integer page, @PathVariable Integer size) {
        Information<Page> information=new Information<>();
        if(followId==null || page==null || size==null){
            information.setMsg("关键信息不可为空");
            information.setStatus(406);
        }else{
            Page<User> followList=new Page<>();
            Integer start=(page-1)*size;
            followList.setDatas(followService.selectByFollowId(followId, start, size));
            Integer total = followService.selectAllCountByFollowId(followId)/size+1;
            followList.setTotalPage(total);
            information.setStatus(200);
            information.setMsg("列表");
            information.setData(followList);
        }
        return information;
    }

    /**
     * 查询关注总数
     * @param followId
     * @return
     */
    @GetMapping("select/follow/num/{followId}")
    public Information selectCountByFollowId(@PathVariable Integer followId){
        Information<Integer> information=new Information<>();
        if(followId==null){
            information.setMsg("关键信息不可为空");
            information.setStatus(406);
        }else{
            Integer count = followService.selectAllCountByFollowId(followId);
            information.setData(count);
            information.setMsg("关注总数");
            information.setStatus(200);
        }
        return information;
    }

    /**
     * 查找粉丝
     *
     * @param followedId 被关注人
     * @return 粉丝列表
     */
    @GetMapping("select/followed/{followedId}/{page}/{size}")
    public Information selectByFollowedId(@PathVariable Integer followedId,@PathVariable Integer page,@PathVariable Integer size) {
        Information<Page> information=new Information<>();
        if(followedId==null || page==null || size==null){
            information.setMsg("关键信息不可为空");
            information.setStatus(406);
        }else{
            Page<User> userPage=new Page<>();
            Integer start=(page-1)*size;
            userPage.setDatas(followService.selectByFollowedId(followedId, start, size));
            Integer total = followService.selectAllCountByFollowedId(followedId)/size+1;
            userPage.setTotalPage(total);
            information.setData(userPage);
            information.setStatus(200);
            information.setMsg("列表");
        }
        return information;
    }

    /**
     * 粉丝总数
     * @param followedId
     * @return
     */
    @GetMapping("select/followed/num/{followedId}")
    public Information selectCountByFollowedId(@PathVariable Integer followedId){
        Information<Integer> information=new Information<>();
        if(followedId==null){
            information.setMsg("关键信息不可为空");
            information.setStatus(406);
        }else{
            Integer count = followService.selectAllCountByFollowedId(followedId);
            information.setData(count);
            information.setMsg("粉丝总数");
            information.setStatus(200);
        }
        return information;
    }




    /**
     * 添加关注记录
     *
     * @param follow 关注记录
     * @return 返回主键值
     */
    @PostMapping("add")
    public Information<Integer> addFollow(Follow follow, HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        boolean verify = Authorization.verify(request, follow.getFollowId());
        if(!verify){
            information.setMsg("非法操作");
            information.setStatus(411);
        }
        if(follow.getFollowedId()==null || follow.getFollowId()==null){
            information.setMsg("关键信息不可为空");
            information.setStatus(406);
        }else{
            Integer result = followService.addFollow(follow);
            if(result==-7){
                information.setStatus(400);
                information.setMsg("关注失败");
            }else if(result==-2){
                information.setStatus(402);
                information.setMsg("重复关注");
            }else{
                information.setMsg("关注成功");
                information.setStatus(200);
                information.setData(result);
            }
        }
        return information;
    }

    /**
     * 取消关注时
     *
     * @param follow 关注记录
     * @return 结果
     */
    @DeleteMapping("delete")
    public Information<Integer> deleteFollow(Follow follow,HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        boolean verify = Authorization.verify(request, follow.getFollowId());
        if(!verify){
            information.setMsg("非法操作");
            information.setStatus(411);
        }else if(follow.getFollowId()==null && follow.getFollowedId()==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else{
            Integer result = followService.deleteFollow(follow);
            if(result<=0){
                information.setMsg("删除失败");
                information.setStatus(400);
            }else{
                information.setStatus(200);
                information.setMsg("删除成功");
                information.setData(result);
            }
        }
        return information;
    }

    /**
     * 某个用户取关所有人
     *
     * @param followId 关注人
     * @return 结果
     */
    @DeleteMapping("delete/all/follow/{followId}")
    public Information<Integer> deleteByFollowId(@PathVariable Integer followId,HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        boolean verify = Authorization.verify(request, followId);
        if(followId==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else if(!verify){
            information.setMsg("非法操作");
            information.setStatus(411);
        }else{
            Integer result = followService.deleteByFollowId(followId);
            if(result<=0){
                information.setMsg("删除失败");
                information.setStatus(400);
            }else{
                information.setStatus(200);
                information.setMsg("删除成功");
                information.setData(result);
            }
        }
        return information;
    }

    /**
     * 某个用户除所有粉丝
     *
     * @param followedId 被关注人
     * @return 结果
     */
    @DeleteMapping("delete/all/followed/{followedId}")
    public Information<Integer> deleteByFollowedId(@PathVariable Integer followedId,HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        boolean verify = Authorization.verify(request, followedId);
        if(followedId==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else if(!verify){
            information.setMsg("非法操作");
            information.setStatus(411);
        }else{
            Integer result = followService.deleteByFollowedId(followedId);
            if(result<=0){
                information.setMsg("删除失败");
                information.setStatus(400);
            }else{
                information.setStatus(200);
                information.setMsg("删除成功");
                information.setData(result);
            }
        }
        return information;
    }
}