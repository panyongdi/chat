package com.chat.server.service;

import com.chat.protocol.ProtocolLogin;
import com.chat.protocol.ProtocolSend;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 */
public interface ChatService {

    public void userLogin(ProtocolLogin protocolLogin, ChannelHandlerContext ctx) throws Exception;

    public void sendMessageFriend(ProtocolSend protocolSend, ChannelHandlerContext ctx) throws Exception;

}
