package com.darren.springbootmall.dao;

import com.darren.springbootmall.pojo.Order;
import com.darren.springbootmall.pojo.OrderItem;

import java.util.List;

public interface OrderDao {
   Integer createOrder(Integer userId, int totalAmount);

   List<OrderItem> getOrderItemsByOrderId(Integer orderId);

   void createOrderItem(Integer orderId, List<OrderItem> orderItemList);

   Order getOrderById(Integer orderId);
}
