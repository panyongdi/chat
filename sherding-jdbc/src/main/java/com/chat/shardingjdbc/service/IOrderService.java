package com.chat.shardingjdbc.service;

import com.chat.shardingjdbc.entity.Order;
import com.chat.shardingjdbc.entity.OrderMessage;

import java.util.List;

/**
 *
 */
public interface IOrderService {

    public List<OrderMessage> getOrderMessageList();

    public List<Order> getOrderList();

    public void addOrderMessage();

    public void addOrder();
}
