package com.chat.shardingjdbc.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author ${author}
 * @since 2021-03-05
 */
@Data
public class Order {


    /**
     * 自增主键
     */
    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 价格
     */
    private BigDecimal num;
    /**
     * 创建时间
     */
    private Date createTime;

}
