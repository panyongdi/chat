package com.chat.mq.service;

import cn.hutool.json.JSONObject;
import com.chat.mq.Const;
import com.chat.mq.entity.Order;
import com.chat.utils.DateUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * 订单信息
 */
@Service
public class OrderService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * 创建订单
     */
    public void createOrder() {
        String orderNo = "CHAT" + DateUtils.parseDateToStr("yyyyMMddHHmmssSSS", new Date());
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setNum(new BigDecimal("22"));
        String sql = new String("insert orders (orderNo,num) value(?,?)");
        jdbcTemplate.update(sql, orderNo, order.getNum());

        sendOrder(order);
    }


    /**
     * 发送消息
     *
     * @param order
     */
    public void sendOrder(Order order) {
        JSONObject jsonObject = new JSONObject(order);
        //插入消息备份表
        String sql = new String("insert order_message (orderNo,num,status) value(?,?,?)");
        jdbcTemplate.update(sql, order.getOrderNo(), order.getNum(),"0");

        CorrelationData correlationData=new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString().replace("-",""));
        rabbitTemplate.convertAndSend(Const.ORDER_EXCHANGE, "", jsonObject,correlationData);
    }

    /**
     * 确认消息发送成功
     */
    @PostConstruct
    public void confirm() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

                System.out.println("投递状态：======>>>>>>" + cause);
                String orderId = correlationData.getId();
                //消息投递失败
                if (!ack) {
                    System.out.println("投递失败：=======>>>>>" + orderId);
                }

                String sql = new String("UPDATE order_message SET status =1 where orderNo=?");
                jdbcTemplate.update(sql, orderId);

            }
        });
    }
}
