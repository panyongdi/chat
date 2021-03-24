package com.chat.protocol;

import lombok.Data;

/**
 * 协议基础信息
 */
@Data
public class Protocol {

    /**
     * 协议code
     */
    private Integer code;

    /**
     * 协议类型
     * 1、登陆 2、发送消息 3、心跳ping值
     */
    private Integer protocol;

    /**
     * uuid
     */
    private String messageId;

    /**
     * 时间
     */
    private String messageDate;
}
