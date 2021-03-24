package com.chat.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * 聊天服务的启动类
 */
@Service
@Slf4j
public class ChatSpringBootInitRunner implements ApplicationRunner {

    @Value("${netty.port:9096}")
    private int port;

    public void run(ApplicationArguments args) throws Exception {
        //启动成功
        log.info("init chatServer port : {}", port);
        //创建服务
        ChatServer chatServer = new ChatServer();
        chatServer.start(port);


    }
}
