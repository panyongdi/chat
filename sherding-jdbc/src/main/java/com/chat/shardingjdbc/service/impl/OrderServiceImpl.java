package com.chat.shardingjdbc.service.impl;

import com.chat.shardingjdbc.entity.Order;
import com.chat.shardingjdbc.entity.OrderMessage;
import com.chat.shardingjdbc.mapper.OrderMapper;
import com.chat.shardingjdbc.service.IOrderService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    OrderMapper orderMapper;

    @Override
    public List<OrderMessage> getOrderMessageList() {
        return orderMapper.getOrderMessageList();
    }

    @Override
    public List<Order> getOrderList() {
        return orderMapper.getOrderList();
    }

    @Override
    public void addOrderMessage() {
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setNum(new BigDecimal(22));
        orderMessage.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
        orderMessage.setStatus(0);
        orderMessage.setId(Math.round(10f));
        orderMapper.addOrderMessage(orderMessage);
    }

    @Override
    public void addOrder() {
        for (int i = 0; i <10; i++) {
            Order order = new Order();
            order.setNum(new BigDecimal(22));
            order.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
            order.setCreateTime(DateUtils.addMonths(new Date(),Math.random() > 0.5 ? 1 : 0));
            orderMapper.addOrder(order);
        }


    }
}
