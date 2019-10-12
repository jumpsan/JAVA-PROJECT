package com.example.bbs.service;

import com.example.bbs.entity.TPost;

import java.util.List;

/**
 * (TPost)表服务接口
 *
 * @author makejava
 * @since 2019-09-20 14:00:31
 */
public interface TPostService {
    TPost selectPostById(Integer id);


    Integer addPost(TPost tPost);

    boolean deletePostById(Integer id);

    boolean deletePostByUserId(Integer id);

    boolean deletePostBySectionId(Integer id);

    boolean deletePostByType(Integer type);

    boolean updatePost(TPost tPost);

    List<TPost> selectPostBySectionId(Integer id, Integer start, Integer num);

    List<TPost> selectPostByUserId(Integer id, Integer start, Integer num);

    List<TPost> selectPostByType(Integer type, Integer start, Integer num);

    List<TPost> selectPostByTitle(String title, Integer start, Integer num);
}