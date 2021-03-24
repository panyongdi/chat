package com.chat.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chat.model.Friend;
import com.chat.model.po.FriendInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 好友表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-03-05
 */

public interface FriendMapper extends BaseMapper<Friend> {

    @Select("SELECT \n" +
            "\tts.id," +
            " ts.user_name as userName," +
            "ts.avatar,\n" +
            "\tfr.link_id as linkId \n" +
            "FROM\n" +
            "\tfriend fr\n" +
            "\tLEFT JOIN t_user ts ON fr.friend_id = ts.id \n" +
            "WHERE\n" +
            "\tfr.user_id =#{userId}")
    public List<FriendInfo> getFriendList(String userId);
}
