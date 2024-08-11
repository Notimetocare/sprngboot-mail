package com.darren.springbootmall.service;

import com.darren.springbootmall.dto.CreateOrderRequest;
import com.darren.springbootmall.dto.OrderQueryParams;
import com.darren.springbootmall.pojo.Order;

import java.util.List;

public interface OrderService {
    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

    Order getOrderById(Integer orderId);

    List<Order> getOrder(OrderQueryParams orderQueryParams);

    Integer countOrder(OrderQueryParams orderQueryParams);
}
