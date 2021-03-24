package com.chat.controller;

import com.alibaba.fastjson.JSONObject;
import com.chat.model.vo.ResponseJson;
import com.chat.mq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/createOrder")
    public ResponseJson createOrder() {
        orderService.createOrder();

        return new ResponseJson().success();
    }

    @PostMapping("/test")
    @ResponseBody
    public ResponseJson test(){
        System.out.println("测试路径");
        return new ResponseJson().success();
    }
}
