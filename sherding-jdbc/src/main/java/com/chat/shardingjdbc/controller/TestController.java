package com.chat.shardingjdbc.controller;

import com.chat.shardingjdbc.entity.Order;
import com.chat.shardingjdbc.entity.OrderMessage;
import com.chat.shardingjdbc.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    IOrderService orderService;

    @RequestMapping("/getOrderMessageList")
    public List<OrderMessage> getOrderMessageList() {
        return orderService.getOrderMessageList();
    }

    @RequestMapping("/addOrderMessage")
    public Map addOrderMessage() {
        orderService.addOrderMessage();
        Map map=new HashMap();
        map.put("code",200);
        return map;
    }

    @RequestMapping("/getOrderList")
    public List<Order> getOrderList() {
        return orderService.getOrderList();
    }
    @RequestMapping("/addOrder")
    public Map addOrder() {
        orderService.addOrder();
        Map map=new HashMap();
        map.put("code",200);
        return map;
    }
}
