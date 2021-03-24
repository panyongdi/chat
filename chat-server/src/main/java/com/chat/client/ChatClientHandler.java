package com.chat.client;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 自定义的解码器
 */
@Slf4j
public class ChatClientHandler extends SimpleChannelInboundHandler<Object> {

    /**
     * 有新连接时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("create client success");

    }


    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = "hello";
        send(ctx, message);
    }

    private static void send(ChannelHandlerContext ctx, String req1) throws NoSuchPaddingException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(req1.getBytes());
        ctx.writeAndFlush(buffer);
    }
}
