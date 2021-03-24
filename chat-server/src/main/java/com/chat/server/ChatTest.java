package com.chat.server;

import com.alibaba.fastjson.JSON;
import com.chat.mongo.entiy.Message;
import com.chat.protocol.ProtocolSend;
import com.chat.utils.BeanUtils;
import com.chat.utils.JwtUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author：silence
 * @Description:
 * @Date:Create in 2021/3/4 9:22
 */
@Slf4j
public class ChatTest {

    public static void main(String[] args) throws Exception {
        String message = "{\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE2MTUzNjQxOTcsInN1YiI6IjIiLCJleHAiOjE2MTYyODU3OTd9._ltUgtNw8FNNLrOmTbEBjP1HNe2cSt-pU8AAH5QNqPJsyV1ZMzYbLHgXacgPskYtscaUkxl7EbG09gJ_-FTUfg\",\"friendId\":\"1\",\"message\":\"23\"," +
                "\"linkId\":\"222\",\"protocol\":2,\"type\":1,\"userId\":\"2\"}";
        ProtocolSend protocolSend = JSON.parseObject(message, ProtocolSend.class);
        Message message1 = new Message();
        BeanUtils.copyValue(message1, protocolSend);

//        NioEventLoopGroup bossGroup=new NioEventLoopGroup(2);
//        NioEventLoopGroup workerGroup=new NioEventLoopGroup(5);
//
//        ServerBootstrap bootstrap=new ServerBootstrap();
//        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
//        bootstrap.option(ChannelOption.TCP_NODELAY, true);
//        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
//
//        bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class);
//        bootstrap.childHandler(new ChannelInitializer(){
//
//            protected void initChannel(Channel channel) throws Exception {
//                ChannelPipeline pipeline=channel.pipeline();
//
//                //解码成HttpRequest
//                pipeline.addLast(new HttpClientCodec());
//                //解决发送大内存东西时内存溢出
//                pipeline.addLast(new ChunkedWriteHandler());
//                //解码成FullHttpRequest
//                pipeline.addLast(new HttpObjectAggregator(1024*10));
//                //添加webSocket解码
//                pipeline.addLast(new WebSocketServerProtocolHandler("/chat"));
//
//                //添加自己的解码器
//                pipeline.addLast(new ChatServerHandler());
//            }
//        });
//        //绑定端口
//        ChannelFuture channelFuture=bootstrap.bind(9096).sync();
//
//        //端口端口
//        channelFuture.channel().closeFuture().sync();
    }


}
