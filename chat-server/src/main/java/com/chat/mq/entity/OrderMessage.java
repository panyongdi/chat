package com.chat.mq.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单备份
 */
@Data
public class OrderMessage {

    public Integer id;

    public String orderNo;

    public BigDecimal num;

    public Integer status;

    public Date createTime;
}
