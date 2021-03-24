package com.chat.service;

import com.baomidou.mybatisplus.service.IService;
import com.chat.model.TUser;
import com.chat.model.po.UserInfo;
import com.chat.model.vo.ResponseJson;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author ${author}
 * @since 2021-03-05
 */
public interface ITUserService extends IService<TUser> {

    public ResponseJson login(String userName, String passWd, HttpSession session) throws Exception;

    public ResponseJson logout(HttpSession session) throws Exception;

    public TUser getUserByToken(String token) throws Exception;

    public ResponseJson getUserInfo(HttpSession session) throws Exception;

    public ResponseJson getHistoryList(String linkId) throws Exception;


}
