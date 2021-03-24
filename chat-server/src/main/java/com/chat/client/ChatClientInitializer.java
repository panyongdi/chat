package com.chat.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

/**
 * netty初始化类
 */
public class ChatClientInitializer extends ChannelInitializer {


    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));

        //添加自己的解码器
        pipeline.addLast(new ChatClientHandler());

    }
}
