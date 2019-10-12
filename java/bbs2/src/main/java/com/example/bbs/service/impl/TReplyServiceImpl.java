package com.example.bbs.service.impl;

import com.example.bbs.entity.TReply;
import com.example.bbs.dao.TReplyDao;
import com.example.bbs.service.TReplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TReply)表服务实现类
 *
 * @author makejava
 * @since 2019-09-20 14:00:45
 */
@Service("tReplyService")
public class TReplyServiceImpl implements TReplyService {
    @Resource
    private TReplyDao tReplyDao;


    /**
     * 根据帖子id查询
     *
     * @param id    帖子id
     * @param start 偏移
     * @param num   行数
     * @return 帖子列表
     */
    @Override
    public List<TReply> selectReplyByPostId(Integer id, Integer start, Integer num) {
        return tReplyDao.selectReplyByPostId(id, start, num);
    }

    /**
     * 根据用户Id查询
     *
     * @param id    用户id
     * @param start
     * @param num
     * @return
     */
    @Override
    public List<TReply> selectReplyByUserId(Integer id, Integer start, Integer num) {
        return tReplyDao.selectReplyByUserId(id, start, num);
    }

    /**
     * 添加回复
     *
     * @param reply
     * @return
     */
    @Override
    public Integer addReply(TReply reply) {
        reply = tReplyDao.addReply(reply);
        return reply.getId();
    }

    /**
     * 删除回复
     *
     * @param id 主键
     * @return
     */
    @Override
    public boolean deleteReplyById(Integer id) {
        return tReplyDao.deleteReplyById(id) > 0;
    }

    /**
     * 修改回复
     *
     * @param reply 回复
     * @return
     */
    @Override
    public boolean updateReply(TReply reply) {
        return tReplyDao.updateReply(reply) > 0;
    }

    /**
     * 根据回复id查询
     *
     * @param id 回复id
     * @return 结果
     */
    @Override
    public TReply selectReplyById(Integer id) {
        return tReplyDao.selectReplyById(id);
    }
}