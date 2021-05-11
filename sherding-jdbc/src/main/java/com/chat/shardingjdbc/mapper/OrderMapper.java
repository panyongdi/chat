package com.chat.shardingjdbc.mapper;

import com.chat.shardingjdbc.entity.Order;
import com.chat.shardingjdbc.entity.OrderMessage;
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
    public List<OrderMessage> getOrderMessageList();

    @Insert("insert into order_message(orderNo,num,status) values(#{orderNo},#{num},#{status})")
    public void addOrderMessage(OrderMessage orderMessage);

    @Insert("insert orders(orderNo,num,createTime) values(#{orderNo},#{num},#{createTime})")
    public void addOrder(Order order);

    @Select("select * from orders order by num")
    public List<Order> getOrderList();
}
