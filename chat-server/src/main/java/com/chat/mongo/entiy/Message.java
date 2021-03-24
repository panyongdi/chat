package com.chat.mongo.entiy;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 发送消息
 */
@Data
@AllArgsConstructor
public class Message {
    public Message() {
    }

    private String messageId;

    private Integer userId;

    private Integer friendId;

    private String message;

    /**
     * 类型
     * 1、文字 2、图片 3、语音
     */
    private Integer type;

    /**
     * 聊天室信息
     */
    private String linkId;

    /**
     * 状态 0、未读 1、已读
     */
    private Integer status;

    /**
     * 日期
     */
    private String messageDate;

}
