package com.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.URI;

/**
 * @Author：silence
 * @Description:
 * @Date:Create in 2021/3/4 10:10
 */
public class ChatClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup clientGroup = new NioEventLoopGroup(1);

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        URI uri = URI.create("ws://localhost:9096/chat");

        bootstrap.remoteAddress(uri.getHost(), uri.getPort());

        bootstrap.handler(new ChatClientInitializer());
        //创建连接
        ChannelFuture channelFuture = bootstrap.connect(uri.getHost(), uri.getPort()).sync();
        //端口连接
        channelFuture.channel().closeFuture().sync();


    }
}
