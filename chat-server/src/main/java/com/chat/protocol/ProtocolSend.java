package com.chat.protocol;

import lombok.Data;

/**
 * 发送消息协议
 */
@Data
public class ProtocolSend extends Protocol {

    /**
     * 聊天室信息
     */
    private String linkId;

    /**
     * 消息类型
     * 1、文本 2、音频 3、图片
     */
    private Integer type;

    /**
     * 消息
     */
    private String message;

    /**
     * 朋友id
     */
    private Integer friendId;

    /**
     * 发送人id
     */
    private Integer userId;

    private String token;
}
