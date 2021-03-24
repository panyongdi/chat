package com.chat.service;

import com.baomidou.mybatisplus.service.IService;
import com.chat.model.Friend;
import com.chat.model.po.FriendInfo;

import java.util.List;


/**
 * <p>
 * 好友表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-05
 */
public interface IFriendService extends IService<Friend> {
    public List<FriendInfo> getFriendList(String userId);
}
