package com.chat.shardingjdbc.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 好友表
 * </p>
 *
 * @author ${author}
 * @since 2021-03-05
 */
@Data
public class OrderMessage {


    /**
     * 自增主键
     */
    private Integer id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 价格
     */
    private BigDecimal num;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;

}
