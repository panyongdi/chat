package com.chat.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chat.model.vo.ResponseJson;
import com.chat.protocol.Protocol;
import com.chat.protocol.ProtocolLogin;
import com.chat.protocol.ProtocolSend;
import com.chat.server.service.ChatService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


/**
 * 自定义的解码器
 */
@Slf4j
@Service
public class ChatServerHandler extends SimpleChannelInboundHandler<Object> {

    @Autowired
    ChatService chatService;

    static ChatService staticChatService;

    @PostConstruct
    public void init() {
        this.staticChatService = chatService;
    }

    /**
     * 有新连接时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connected from address: " + ctx.channel().remoteAddress());

        super.channelActive(ctx);

    }

    /**
     * 删除新连接时调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connected closed address: " + ctx.channel().remoteAddress());
        super.channelInactive(ctx);
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("msg type {}", msg.getClass());
        if (msg instanceof TextWebSocketFrame) {
            TextWebSocketFrame textWebSocketFrame = (TextWebSocketFrame) msg;
            log.info("Message : {}", textWebSocketFrame.text());

            String request = textWebSocketFrame.text();
            JSONObject param = null;
            try {
                param = JSON.parseObject(request);
            } catch (Exception e) {
                sendMessage(ctx, "JSON字符串转换出错");
                e.getMessage();
            }
            Integer type = param.getInteger("protocol");
            switch (type) {
                case 1:
                    //注册登陆
                    staticChatService.userLogin(JSON.parseObject(request, ProtocolLogin.class), ctx);
                    break;
                case 2:
                    //发送消息
                    staticChatService.sendMessageFriend(JSON.parseObject(request, ProtocolSend.class), ctx);
                    break;
            }
        }
    }

    //发送消息
    public void sendMessage(ChannelHandlerContext ctx, String errorMsg) {
        String responseJson = new ResponseJson()
                .error(errorMsg)
                .toString();
        ctx.channel().writeAndFlush(new TextWebSocketFrame(responseJson));
    }
}
