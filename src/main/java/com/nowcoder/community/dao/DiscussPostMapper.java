package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //支持分页
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //注解，如果在sql用动态条件，用这个条件有且只有一个这个参数，条件用到这个参数，一定写注解
    //用于给方法取别名
    int selectDiscussPostRows(@Param("userId") int userId);



}
