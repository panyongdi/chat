package com.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * netty初始化类
 */
public class ChatServerInitializer extends ChannelInitializer {


    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        //解码成HttpRequest
        pipeline.addLast("http-codec", new HttpServerCodec());
        //解决发送大内存东西时内存溢出
        pipeline.addLast(new ChunkedWriteHandler());
        //解码成FullHttpRequest
        pipeline.addLast("aggregator", new HttpObjectAggregator(1024 * 10));
        //添加webSocket解码
//        pipeline.addLast(new WebSocketServerProtocolHandler("/chat"));

        //添加自定义的httpRequest解码
        pipeline.addLast(new HttpRequestHandler());
        //添加自己的解码器
        pipeline.addLast(new ChatServerHandler());

    }
}
