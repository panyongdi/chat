package com.chat.service.impl;

import com.chat.mapper.FriendMapper;
import com.chat.model.Friend;
import com.chat.model.po.FriendInfo;
import com.chat.service.IFriendService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 好友表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-05
 */
@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend> implements IFriendService {

    @Autowired
    FriendMapper friendMapper;

    /**
     * 查询用户朋友列表
     *
     * @param userId
     * @return
     */
    public List<FriendInfo> getFriendList(String userId) {
        return friendMapper.getFriendList(userId);
    }
}
