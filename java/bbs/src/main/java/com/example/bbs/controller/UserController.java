package com.example.bbs.controller;

import com.example.bbs.dto.UserDto;
import com.example.bbs.entity.Information;
import com.example.bbs.entity.Page;
import com.example.bbs.entity.User;
import com.example.bbs.service.RoleService;
import com.example.bbs.service.UserService;
import com.example.bbs.utils.Authorization;
import com.example.bbs.utils.JjwtUtils;
import com.example.bbs.utils.UploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * (TUser)表控制层
 *
 * @author makejava
 * @since 2019-09-20 14:01:19
 */
@RestController
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;


    /**
     * 根据账号查询
     *
     * @param id
     * @return
     */
    @GetMapping("user/select/{id}")
    public Information selectUserById(@PathVariable Integer id) {
        Information<User> information=new Information<>();
        if(id==null){
            information.setMsg("用户id不能为空");
            information.setStatus(406);
        }else {
            User user = userService.selectUserById(id);
            if(user==null){
                information.setMsg("查询用户不存在");
                information.setStatus(204);
            }else{
                user.setPassword("*");
                information.setData(user);
                information.setStatus(200);
                information.setMsg("用户");
            }
        }
        return information;
    }

    /**
     * 根据用户名密码查询
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    @PostMapping("user/login")
    public Information<String> selectUserByNameAndPassword(String username, String password, HttpServletResponse response) {
        User user = userService.selectUserByNameAndPassword(username, password);
        Information<String> information =new Information<>();
        String msg = "";
        if (user == null) {
            msg = "用户名或密码错误";
            information.setStatus(202);
            information.setMsg(msg);
        } else {
            try {
                String token = JjwtUtils.createJWT(user.getId(), 15 * 60 * 1000);
                response.setHeader("token",token);
                msg = "用户";
                user.setPassword("*");
                information.setData(token);
                information.setStatus(200);
                information.setMsg(msg);
            } catch (Exception e) {
                e.printStackTrace();
                information.setStatus(202);
                information.setMsg("未知错误");
            }
        }
        return information;
    }

    /**
     * 添加用户
     * 返回主键
     * @param userDto 用户信息
     * @return 账号
     */
    @PostMapping("user/register")
    public Information addUser(UserDto userDto) {
        Information<Integer> information=new Information<>();
        User user = userDto.getUser();
        MultipartFile multipartFile = userDto.getMultipartFile();
        String newName="";
        if(multipartFile!=null){
             newName= UploadUtils.getNewName(multipartFile);
            if(newName==null){
                information.setStatus(410);
                information.setMsg("文件上传失败");
                return information;
            }
            user.setImage(newName);
        }
        if(user==null || user.getPassword()==null || user.getUsername()==null){
            information.setMsg("用户名或密码不能为空");
            information.setStatus(406);
        }else{
            Integer userId = userService.addUser(user);
            information.setData(userId);
            if( userId>0){
                if(multipartFile!=null){
                    boolean result = UploadUtils.uploadFile(multipartFile, 2, newName);
                    if(!result){
                        information.setMsg("头像上传失败，但已注册成功");
                        information.setStatus(410);
                    }
                }else{
                    information.setMsg("用户主键");
                    information.setStatus(200);
                }
            }else if(userId==-2){
                information.setMsg("用户名不能重复");
                information.setStatus(402);
            }
        }
        return information;
    }

    /**
     * 根据账号删除用户
     *
     * @param id 账号
     * @return 结果
     */
    @DeleteMapping("user/delete/{id}")
    public Information<Integer> deleteUser(@PathVariable Integer id, HttpServletRequest request) {
        Information<Integer> information=new Information<>();
        User user = userService.selectUserById(id);
        String msg="";
        Integer status=202;
        boolean verify = Authorization.verify(request, user.getId());
        if(!verify){
            msg="非法操作";
            status=411;
        }else{
            Integer result = userService.deleteUserById(id);
            UploadUtils.deleteFile(user.getImage(),2);//删除头像

            if(result>0){
                msg="删除成功";
                status=200;
                information.setData(result);
            }
            else {
                msg = "删除失败";
                status=400;
            }
        }
        information.setStatus(status);
        information.setMsg(msg);
        return information;
    }

    /**
     * 修改用户信息
     *
     * @param userDto 用户
     * @return 结果
     */
    @PutMapping("user/update")
    public Information updateUser(UserDto userDto,HttpServletRequest request) {
        User user = userDto.getUser();
        Information<Integer> information=new Information<>();
        boolean verify = Authorization.verify(request, user.getId());

        if(user.getId()==null){
            information.setStatus(403);
            information.setMsg("用户id不可为空");
        }else if(!verify){
            information.setMsg("非法操作");
            information.setStatus(411);
        }else{
            MultipartFile multipartFile = userDto.getMultipartFile();
            String newName="";
            User checkUser = userService.selectUserById(user.getId());
            if(multipartFile!=null){
                newName= UploadUtils.getNewName(multipartFile);
                if(newName==null){
                    information.setStatus(410);
                    information.setMsg("文件上传失败");
                    return information;
                }
                user.setImage(newName);
            }
            Integer result = userService.updateUserById(user);
            if(result>0){
                information.setMsg("更新成功");
                information.setStatus(200);
                UploadUtils.deleteFile(checkUser.getImage(),2);
            }else if(result==-2){
                information.setMsg("用户名重复");
                information.setStatus(402);
            }
            else {
                information.setMsg("更新失败");
                information.setStatus(400);
            }
            information.setData(result);
        }
        return information;
    }

    /**
     * 分页查询用户
     * @param page
     * @param size
     * @return
     */
    @GetMapping("admin/select/all/user/{page}/{size}")
    public Information selectAllUser(@PathVariable Integer page,@PathVariable Integer size){
        Information<Page> information=new Information<Page>();
        Integer totalPage=userService.selectAllUserCount()/size+1;
        Integer start=(page-1)*size;
        List<User> users= userService.selectAllUser(start, size);
        Page<User> pageObject=new Page<>();
        pageObject.setDatas(users);
        pageObject.setTotalPage(totalPage);
        information.setData(pageObject);
        if(users==null){
            information.setMsg("空");
            information.setStatus(204);
        }else{
            information.setMsg("成功");
            information.setStatus(200);
        }
        return information;
    }

}