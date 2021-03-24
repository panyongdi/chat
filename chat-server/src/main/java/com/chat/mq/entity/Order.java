package com.chat.mq.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */
@Data
public class Order {

    public Integer id;

    public String orderNo;

    public BigDecimal num;

    public Date createTime;
}
