package com.example.bbs.entity;

import java.io.Serializable;

/**
 * (TBlacklist)实体类
 *
 * @author makejava
 * @since 2019-09-22 14:36:36
 */
public class TBlacklist implements Serializable {
    private static final long serialVersionUID = 941127488410566388L;

    private Integer id;
    //用户编号
    private Integer userId;
    //权限编号；0禁评论，1禁发帖
    private Integer permission;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

}