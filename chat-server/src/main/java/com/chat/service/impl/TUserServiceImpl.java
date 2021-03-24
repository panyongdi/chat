package com.chat.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.chat.constant.Const;
import com.chat.mapper.TUserMapper;
import com.chat.model.TUser;
import com.chat.model.po.UserInfo;
import com.chat.model.vo.ResponseJson;
import com.chat.mongo.entiy.Message;
//import com.chat.mongo.service.MongoService;
import com.chat.server.redis.RedisUtil;
import com.chat.service.IFriendService;
import com.chat.service.ITUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.chat.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-05
 */
@Service
@Slf4j
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    public final static String secret = "chat";

    public final static Long ttlSeconds = 60 * 60 * 256l;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    IFriendService friendService;

//    @Autowired
//    MongoService mongoService;

    /**
     * 用户登陆
     *
     * @param userName
     * @param passWd
     * @param session
     * @return
     */
    public ResponseJson login(String userName, String passWd, HttpSession session) throws Exception {

        EntityWrapper<TUser> userEntityWrapper = new EntityWrapper<TUser>();
        userEntityWrapper.eq("user_name", userName).eq("password", passWd);
        TUser user = this.selectOne(userEntityWrapper);
        if (user == null) {
            return new ResponseJson().error("检查用户名或密码是否正确");
        }
        String jwt = JwtUtil.createJWT(secret, user.getId().toString(), ttlSeconds);
        log.info("jwt {}", jwt);
        session.setAttribute(Const.USER_TOKEN, jwt);

        return new ResponseJson().success();
    }

    public ResponseJson logout(HttpSession session) throws Exception {
        Object jwt = session.getAttribute(Const.USER_TOKEN);
        if (jwt == null) {
            return new ResponseJson().error("请先登陆");
        }
        session.removeAttribute(Const.USER_TOKEN);

        TUser user = getUserByToken(String.valueOf(jwt));
        redisUtil.delete(user.getId().toString());

        return new ResponseJson().success();
    }

    /**
     * 根据token查询用户
     *
     * @param token
     * @return
     */
    public TUser getUserByToken(String token) throws Exception {
        Claims claims = JwtUtil.parseJWT(secret, token);
        String userId = claims.getSubject();
        TUser user = this.selectById(userId);
        return user;
    }

    /**
     * 获取当前用户信息
     *
     * @param session
     * @return
     */
    public ResponseJson getUserInfo(HttpSession session) throws Exception {
        Object token = session.getAttribute(Const.USER_TOKEN);

        TUser user = getUserByToken(String.valueOf(token));

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId().toString());
        userInfo.setAvatarUrl(user.getAvatar());
        userInfo.setUsername(user.getUserName());
        userInfo.setToken(String.valueOf(token));
//
        userInfo.setFriendList(friendService.getFriendList(user.getId().toString()));

        return new ResponseJson().success().setData("userInfo", userInfo);
    }

    public ResponseJson getHistoryList(String linkId) throws Exception {
//        List<Message> messageList=mongoService.getMessageListByLinkId(linkId);
//        return new ResponseJson().success().setData("historyList",messageList);
        return new ResponseJson().success();
    }
}
