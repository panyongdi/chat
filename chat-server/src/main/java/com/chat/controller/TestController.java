package com.chat.controller;

import com.chat.model.vo.ResponseJson;
import com.chat.service.IFriendService;
import com.chat.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试控制器
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private IFriendService friendService;

    @RequestMapping("/testController")
    @ResponseBody
    public ResponseJson TestController() {
        return new ResponseJson().success();
    }
}
