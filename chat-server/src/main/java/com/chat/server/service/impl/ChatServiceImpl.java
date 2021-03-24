package com.chat.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chat.constant.Const;
import com.chat.model.TUser;
import com.chat.mongo.entiy.Message;
//import com.chat.mongo.service.MongoService;
import com.chat.protocol.ProtocolLogin;
import com.chat.protocol.ProtocolSend;
import com.chat.server.redis.RedisUtil;
import com.chat.server.service.ChatService;
import com.chat.service.ITUserService;
import com.chat.utils.BeanUtils;
import com.chat.utils.DateUtils;
import com.chat.utils.JwtUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.chat.utils.DateUtils.DATE_TIME_PATTERN;

/**
 * 实现类
 */
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    ITUserService userService;

//    @Autowired
//    MongoService mongoService;

    /**
     * 用户注册
     *
     * @param protocolLogin
     * @param ctx
     */
    public void userLogin(ProtocolLogin protocolLogin, ChannelHandlerContext ctx) throws Exception {
        //获取用户的token
        String jwt = protocolLogin.getToken();
        TUser user = userService.getUserByToken(jwt);

        //用户在线
        Const.onlineMap.put(user.getId().toString(), ctx);

        protocolLogin.setMessageId(UUID.randomUUID().toString().replace("-", ""));
        protocolLogin.setMessageDate(DateUtils.dateFormat(new Date(), DATE_TIME_PATTERN));
        sendMessage(ctx, JSONObject.toJSONString(protocolLogin));

    }

    /**
     * 用户发送消息
     *
     * @param protocolSend
     * @param ctx
     * @throws Exception
     */
    public void sendMessageFriend(ProtocolSend protocolSend, ChannelHandlerContext ctx) throws Exception {
        Integer friendId = protocolSend.getFriendId();
        protocolSend.setMessageId(UUID.randomUUID().toString().replace("-", ""));
        protocolSend.setMessageDate(DateUtils.dateFormat(new Date(), DateUtils.DATE_TIME_PATTERN));

        TUser user = userService.getUserByToken(protocolSend.getToken());
        protocolSend.setUserId(user.getId());
        ChannelHandlerContext friendCtx = Const.onlineMap.get(String.valueOf(friendId));
        //用户在线
        if (friendCtx != null) {
            sendMessage(friendCtx, JSON.toJSONString(protocolSend));
        }
        Message message = new Message();
        BeanUtils.copyValue(message, protocolSend);
        message.setStatus(0);

//        mongoService.insertObject(message);
    }

    //发送消息
    public void sendMessage(ChannelHandlerContext ctx, String message) {
        ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
    }
}
