package com.example.bbs.controller;

import com.example.bbs.entity.Blacklist;
import com.example.bbs.entity.Information;
import com.example.bbs.service.BlacklistService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TBlacklist)表控制层
 *
 * @author makejava
 * @since 2019-09-22 14:20:30
 */
@RestController
public class BlacklistController {
    /**
     * 服务对象
     */
    @Resource
    private BlacklistService blacklistService;

    /**
     * 根据用户id查询
     *
     * @param id 用户id
     * @return 黑名单列表
     */
    @GetMapping("manager/blacklist/select/user/{id}")
    public Information<List> selectListByUserId(@PathVariable Integer id) {
        Information<List> information=new Information<>();
        if(id==null){
            information.setMsg("用户id为空");
            information.setStatus(406);
        }else{
            List<Blacklist> blacklists = blacklistService.selectListByUserId(id);
            if(blacklists!=null){
                information.setMsg("查询结果");
                information.setData(blacklists);
            }else{
                information.setMsg("该用户不在黑名单中");
            }
            information.setStatus(200);
        }
        return information;
    }

    /**
     * 根据权限和用户id查询
     * @param userId
     * @param permission
     * @return
     */
    @GetMapping("manager/blacklist/select/{userId}/{permission}")
    public Information<Blacklist> selectListByUserIdAndPermission(@PathVariable Integer userId, @PathVariable Integer permission){
        Information<Blacklist> information=new Information<>();
        if(userId==null || permission==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else{
            Blacklist blacklist = blacklistService.selectListByUserIdAndPermission(userId, permission);
            information.setMsg("查询结果");
            information.setStatus(200);
            information.setData(blacklist);
        }
        return information;
    }


    /**
     * 添加黑名单
     *
     * @param blacklist 信息
     * @return 主键值
     */
    @PostMapping("manager/blacklist/add")
    public Information<Integer> addBlackList(Blacklist blacklist) {
        Information<Integer> information=new Information<>();
        if(blacklist.getUserId()==null || blacklist.getPermission()==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else{
            Integer result = blacklistService.addBlackList(blacklist);
            if (result==-7){
                information.setMsg("添加失败");
                information.setStatus(400);
            }else if(result==-2){
                information.setMsg("添加重复");
                information.setStatus(402);
            }else{
                information.setStatus(200);
                information.setMsg("主键");
                information.setData(result);
            }
        }
        return information;
    }

    /**
     * 删除黑名单
     *
     * @param id 黑名单编号
     * @return 结果
     */
    @DeleteMapping("manager/blacklist/delete/{id}")
    public Information deleteBlackListById(@PathVariable Integer id) {
        Information<Integer> information=new Information<>();
        Integer result = blacklistService.deleteBlackListById(id);
        if(result<=0){
            information.setMsg("删除失败");
            information.setStatus(400);
        }else{
            information.setStatus(200);
            information.setMsg("删除成功");
            information.setData(result);
        }
        return information;
    }

}