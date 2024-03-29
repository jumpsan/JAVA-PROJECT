package com.example.bbs.service.impl;

import com.example.bbs.dao.*;
import com.example.bbs.entity.Post;
import com.example.bbs.entity.User;
import com.example.bbs.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TUser)表服务实现类
 *
 * @author makejava
 * @since 2019-09-20 14:01:19
 */
@Service("tUserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Resource
    private PostDao postDao;
    @Resource
    private ApproveDao approveDao;
    @Resource
    private BlacklistDao blacklistDao;
    @Resource
    private CollectDao collectDao;
    @Resource
    private FollowDao followDao;
    @Resource
    private MessageDao messageDao;
    @Resource
    private ReplyDao replyDao;
    @Resource
    private RoleDao roleDao;

    /**
     * 通过账号选择用户
     *
     * @param id 账号
     * @return 用户
     */
    @Override
    public User selectUserById(Integer id) {
        return userDao.selectUserById(id);
    }

    /**
     * 通过账号和密码查找
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    @Override
    public User selectUserByNameAndPassword(String username, String password) {
        return userDao.selectUserByNameAndPassword(username, password);
    }

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 账号
     */
    @Override
    public Integer addUser(User user) {
        //用户名不能重复
        User checkUser = userDao.selectUserByName(user.getUsername());
        if(checkUser==null){
            userDao.addUser(user);
            return user.getId();
        }else{
            return -2;//名称重复
        }

    }

    /**
     * 删除用户
     *
     * @param id 用户账号
     * @return 结果
     */
    @Override
    public Integer deleteUserById(Integer id) {
        //TODO 删除相关信息:消息、黑名单、关注、如果是版主也删除
        postDao.deletePostByUserId(id);
        approveDao.deleteApproveByUserId(id);
        collectDao.deleteCollectByUserId(id);
        replyDao.deleteReplyByUserId(id);
        return userDao.deleteUserById(id);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户需要修改的信息
     * @return 结果
     */
    @Override
    public Integer updateUserById(User user) {
        User checkUser = userDao.selectUserById(user.getId());
        if(!checkUser.getUsername().equals(user.getUsername())){
            //检查用户名重复
            User  checkName = userDao.selectUserByName(user.getUsername());
            if(checkName!=null){
                return -2;//用户名重复
            }
        }
        return userDao.updateUserById(user);
    }

    @Override
    public Integer selectAllUserCount() {
        return userDao.selectAllUserCount();

    }

    @Override
    public List<User> selectAllUser(Integer start, Integer size) {
        return userDao.selectAllUser(start,size);
    }


}