package com.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * server的配置
 */
public class ChatServer {

    public static ServerBootstrap bootstrap = null;

    //先创建一个群组来管理nio
    NioEventLoopGroup bossGroup = new NioEventLoopGroup();

    //创建几个群组来处理连接跟消息处理
    NioEventLoopGroup worGroup = new NioEventLoopGroup(5);

    private void init() {
        bootstrap = new ServerBootstrap();
        //创建netty的启动类
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();

        NioEventLoopGroup worGroup = new NioEventLoopGroup(5);
        bootstrap.group(bossGroup, worGroup);
        //初始化
        bootstrap.channel(NioServerSocketChannel.class).childHandler(new ChatServerInitializer());

    }

    public void start(int port) {

        try {
            this.init();
            //绑定端口
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            System.out.println("开始启动 port:" + port);
            //端口连接
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            System.out.println("端口被占用");
            bossGroup.shutdownGracefully();
            worGroup.shutdownGracefully();
            return;
        }
    }

}
