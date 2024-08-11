package com.darren.springbootmall.service;

import com.darren.springbootmall.dto.CreateOrderRequest;
import com.darren.springbootmall.pojo.Order;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);
}
