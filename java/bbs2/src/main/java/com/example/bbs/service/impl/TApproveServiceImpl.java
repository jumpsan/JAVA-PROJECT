package com.example.bbs.service.impl;

import com.example.bbs.entity.TApprove;
import com.example.bbs.dao.TApproveDao;
import com.example.bbs.service.TApproveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TApprove)表服务实现类
 *
 * @author makejava
 * @since 2019-09-22 14:04:17
 */
@Service("tApproveService")
public class TApproveServiceImpl implements TApproveService {
    @Resource
    private TApproveDao tApproveDao;


    /**
     * 根据帖子Id查找
     *
     * @param id 帖子id
     * @return 点赞列表
     */
    @Override
    public List<TApprove> selectApproveByPostId(Integer id) {
        return tApproveDao.selectApproveByPostId(id);
    }

    /**
     * 添加点赞记录
     *
     * @param approve 点赞信息
     * @return 主键值
     */
    @Override
    public TApprove addApprove(TApprove approve) {
        return tApproveDao.addApprove(approve);
    }

    /**
     * 删除点赞记录
     *
     * @param id 点赞id
     * @return 结果
     */
    @Override
    public boolean deleteApprove(Integer id) {
        return tApproveDao.deleteApprove(id) > 0;
    }
}