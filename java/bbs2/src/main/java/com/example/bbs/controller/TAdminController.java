package com.example.bbs.controller;

import com.example.bbs.entity.TAdmin;
import com.example.bbs.service.TAdminService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * (TAdmin)表控制层
 *
 * @author makejava
 * @since 2019-09-22 13:37:51
 */
@RestController
@RequestMapping("tAdmin")
public class TAdminController {
    /**
     * 服务对象
     */
    @Resource
    private TAdminService tAdminService;

    /**
     * 根据管理员账号和密码查询
     *
     * @param id       账号
     * @param password 密码
     * @return 管理员
     */
    @GetMapping("/doLogin/{id}/{password}")
    public void  selectAdminByIdAndPassword(@PathVariable("id") Integer id, @PathVariable("password") String password, HttpSession session, Model model) {
        TAdmin admin = tAdminService.selectAdminByIdAndPassword(id, password);
        String message = "";
        if (admin == null) {
            message = "用户名或密码错误";
        } else {
            message = "登录成功";
            session.setAttribute("admin_session", admin);
        }
        model.addAttribute("message",message);
    }

    /**
     * 根据账号查询
     *
     * @param id 账号
     * @return 结果
     */
    @GetMapping("selectAdminById")
    public TAdmin selectAdminById(Integer id) {
        return tAdminService.selectAdminById(id);
    }

    /**
     * 添加管理员
     *
     * @param admin 管理员信息
     * @return 账号
     */
    @PostMapping("addAdmin")
    public Integer addAdmin(TAdmin admin) {
        admin = tAdminService.addAdmin(admin);
        return admin.getId();
    }

    /**
     * 根据账号删除管理员
     *
     * @param id 账号
     * @return 结果
     */
    @GetMapping("deleteAdminById")
    public boolean deleteAdminById(Integer id) {
        return tAdminService.deleteAdminById(id);
    }


}