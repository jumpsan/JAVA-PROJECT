package com.example.bbs.dao;

import com.example.bbs.entity.TPost;

import java.util.List;

/**
 * (TPost)表数据库访问层
 *
 * @author makejava
 * @since 2019-09-20 14:00:31
 */
public interface TPostDao {
    TPost selectPostById(Integer id);

    TPost addPost(TPost tPost);

    Integer deletePostById(Integer id);

    Integer deletePostByUserId(Integer id);

    Integer deletePostBySectionId(Integer id);

    Integer deletePostByType(Integer type);

    Integer updatePost(TPost tPost);

    List<TPost> selectPostBySectionId(Integer id, Integer start, Integer num);

    List<TPost> selectPostByUserId(Integer id, Integer start, Integer num);

    List<TPost> selectPostByType(Integer type, Integer start, Integer num);

    List<TPost> selectPostByTitle(String title, Integer start, Integer num);
}