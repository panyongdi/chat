package com.chat.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.chat.model.TUser;
import com.chat.model.po.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2021-03-05
 */
@Mapper
public interface TUserMapper extends BaseMapper<TUser> {


}
