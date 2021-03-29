package com.chat.shardingjdbc.com.shardingjdbc.service.impl;

import com.chat.shardingjdbc.com.shardingjdbc.entity.OrderMessage;
import com.chat.shardingjdbc.com.shardingjdbc.mapper.OrderMapper;
import com.chat.shardingjdbc.com.shardingjdbc.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public List<OrderMessage> getOrderList() {
        return orderMapper.getOrderList();
    }

    @Override
    public void addOrder() {
        OrderMessage orderMessage=new OrderMessage();
        orderMessage.setNum(new BigDecimal(22));
        orderMessage.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        orderMessage.setStatus(0);
        orderMapper.addOrder(orderMessage);
    }
}
