package com.chat.constant;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 常用的静态类
 */
public class Const {

    public static final String USER_TOKEN = "token";

    public static final String LINK_ID = "linkId";


    /**
     * 用户对应的在线连接
     * ChannelHandlerContext
     */
    public static ConcurrentMap<String, ChannelHandlerContext> onlineMap =
            new ConcurrentHashMap<String, ChannelHandlerContext>();

}
