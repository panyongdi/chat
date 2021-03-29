package com.chat.shardingjdbc.com.shardingjdbc.service;

import com.chat.shardingjdbc.com.shardingjdbc.entity.OrderMessage;

import java.util.List;

/**
 *
 */
public interface IOrderService {

    public List<OrderMessage> getOrderList();

    public void addOrder();
}
