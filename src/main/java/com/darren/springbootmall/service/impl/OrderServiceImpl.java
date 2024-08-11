package com.darren.springbootmall.service.impl;

import com.darren.springbootmall.dao.OrderDao;
import com.darren.springbootmall.dao.ProductDao;
import com.darren.springbootmall.dto.BuyItem;
import com.darren.springbootmall.dto.CreateOrderRequest;
import com.darren.springbootmall.pojo.OrderItem;
import com.darren.springbootmall.pojo.Product;
import com.darren.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;
    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());
            //價錢計算
            int amount = buyItem.getQuantity()*product.getPrice();
            totalAmount = totalAmount + amount;

            //轉換BuyItem to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }


        Integer orderId =  orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItem(orderId,orderItemList);

        return orderId;
    }
}
