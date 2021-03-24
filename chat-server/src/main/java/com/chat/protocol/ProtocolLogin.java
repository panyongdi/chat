package com.chat.protocol;

import lombok.Data;

/**
 * 登陆协议
 */
@Data
public class ProtocolLogin extends Protocol {

    /**
     * 用户token
     */
    private String token;


}
