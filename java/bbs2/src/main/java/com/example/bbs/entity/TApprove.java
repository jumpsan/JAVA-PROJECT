package com.example.bbs.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (TApprove)实体类
 *
 * @author makejava
 * @since 2019-09-22 14:04:15
 */
public class TApprove implements Serializable {
    private static final long serialVersionUID = 893786367514939819L;
    //点赞编号
    private Integer id;
    //用户编号
    private Integer userId;
    //点赞时间
    private Date time;
    //帖子编号
    private Integer postId;


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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

}