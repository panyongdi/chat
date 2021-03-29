package com.chat.shardingjdbc.com.shardingjdbc.controller;

import com.chat.shardingjdbc.com.shardingjdbc.entity.OrderMessage;
import com.chat.shardingjdbc.com.shardingjdbc.service.IOrderService;
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

    @RequestMapping("/orderList")
    public List<OrderMessage> getOrderList() {
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
