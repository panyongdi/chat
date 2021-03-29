package com.chat.shardingjdbc.com.shardingjdbc.mapper;

import com.chat.shardingjdbc.com.shardingjdbc.entity.OrderMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 */
@Mapper
public interface OrderMapper {


    @Select("select * from order_message")
    public List<OrderMessage> getOrderList();

    @Insert("insert into order_message(orderNo,num,status) values(#{orderNo},#{num},#{status})")
    public void addOrder(OrderMessage orderMessage);

}
