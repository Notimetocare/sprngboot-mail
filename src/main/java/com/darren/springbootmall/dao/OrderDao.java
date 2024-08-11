package com.darren.springbootmall.dao;

import com.darren.springbootmall.pojo.OrderItem;

import java.util.List;

public interface OrderDao {
   Integer createOrder(Integer userId, int totalAmount);

   void createOrderItem(Integer orderId, List<OrderItem> orderItemList);
}
