package com.example.bbs.controller;

import com.example.bbs.entity.Information;
import com.example.bbs.entity.Plate;
import com.example.bbs.entity.Role;
import com.example.bbs.entity.User;
import com.example.bbs.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TRole)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:00:56
 */
@RestController
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 根据板块Id查找版主
     *
     * @param id 板块id
     * @return 列表版主
     */
    @GetMapping("role/select/plate/{id}")
    public Information<List> selectRoleByPlateId(@PathVariable Integer id) {
        Information<List> information=new Information<>();
        if(id==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else{
            List<User> users = roleService.selectRoleByPlateId(id);
            information.setMsg("版主");
            information.setStatus(200);
            information.setData(users);
        }
        return information;
    }

    /**
     * 根据用户id查找其所管理的板块
     *
     * @param id 用户id
     * @return 板主列表
     */
    @GetMapping("role/select/user/{id}")
    public Information<List> selectRoleByUserId(@PathVariable Integer id) {
        Information<List> information=new Information<>();
        if(id==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else{
            List<Plate> plates = roleService.selectRoleByUserId(id);
            information.setData(plates);
            information.setMsg("板块");
            information.setStatus(200);
        }
        return information;
    }



    /**
     * 添加版主
     *
     * @param role 版主信息
     * @return 主键值
     */
    @PostMapping("manager/role/add")
    public Information<Integer> addRole(Role role) {
        Information<Integer> information=new Information<>();
        if(role.getPlateId()==null || role.getUserId()==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else {
            Integer result = roleService.addRole(role);
            if (result == -7) {
                information.setMsg("添加失败");
                information.setStatus(400);
            } else if(result==-2){
                information.setMsg("重复添加");
                information.setStatus(402);
            } else {
                information.setStatus(200);
                information.setMsg("主键");
                information.setData(result);
            }
        }
        return information;
    }

    /**
     * 根据版主id删除
     *
     * @param id 版主id
     * @return 结果
     */
    @DeleteMapping("manager/role/delete/{id}")
    public Information<Integer> deleteRoleById(@PathVariable Integer id) {
        Information<Integer> information=new Information<>();
        if(id==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else{
            Integer result = roleService.deleteRoleById(id);
            if(result<=0){
                information.setStatus(400);
                information.setMsg("删除失败");
            }else{
                information.setStatus(200);
                information.setMsg("删除成功");
                information.setData(result);
            }
        }
        return information;
    }

    /**
     * 根据用户id以及板块id删除
     * @param plateId
     * @param userId
     * @return
     */
    @DeleteMapping("manager/role/delete/plate/user/{plateId}/{userId}")
    public Information deleteRoleByPlateIdAndUserId(@PathVariable Integer plateId,@PathVariable Integer userId){
        Information<Integer> information=new Information<>();
        if(plateId==null || userId==null){
            information.setStatus(406);
            information.setMsg("关键信息不可为空");
        }else{
            Integer result = roleService.deleteRoleByPlateIdAndUserId(plateId, userId);
            if(result<=0){
                information.setStatus(400);
                information.setMsg("删除失败");
            }else{
                information.setStatus(200);
                information.setMsg("删除成功");
                information.setData(result);
            }
        }
        return information;
    }

}