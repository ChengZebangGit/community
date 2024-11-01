package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int uesrId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(uesrId, offset, limit);
    }

    //查询的时候关联用户，或者单独处理每个userId，方便Redis缓存数据。
    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
    //根据userId查到方法，添加一个跟User相关的操作
}
