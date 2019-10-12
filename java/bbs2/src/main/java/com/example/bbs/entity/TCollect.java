package com.example.bbs.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (TCollect)实体类
 *
 * @author makejava
 * @since 2019-09-20 13:25:14
 */
@Data
public class TCollect implements Serializable {
    private static final long serialVersionUID = -25715935744453745L;
    //收藏人用户编号
    private Integer userId;
    //收藏的帖子编号
    private Integer postId;
    private Integer Id;


}