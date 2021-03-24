package com.chat.controller;


import com.chat.constant.Const;
import com.chat.model.TUser;
import com.chat.model.vo.ResponseJson;
import com.chat.service.ITUserService;
import com.chat.service.impl.TUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chatroom")
public class ChatroomController {

    @Autowired
    ITUserService userService;

    /**
     * 登录成功后，调用此接口进行页面跳转
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toChatroom() {
        return "chatroom";
    }

    /**
     * 登录成功跳转页面后，调用此接口获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "/get_userinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson getUserInfo(HttpSession session) {
        Object token = session.getAttribute(Const.USER_TOKEN);
        try {
            return userService.getUserInfo(session);
        } catch (Exception e) {
            return new ResponseJson().error("用户信息不存在");
        }
    }

    /**
     * 获取用户历史记录
     *
     * @return
     */
    @RequestMapping(value = "/getHistoryList", method = RequestMethod.POST)
    @ResponseBody
    public ResponseJson getHistoryList(HttpServletRequest request) {
        try {
            String linkId = request.getParameter("linkId");
            return userService.getHistoryList(linkId);
        } catch (Exception e) {
            return new ResponseJson().error("用户信息不存在");
        }
    }
}
