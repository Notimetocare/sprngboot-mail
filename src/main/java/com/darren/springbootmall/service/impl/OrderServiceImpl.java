package com.darren.springbootmall.service.impl;

import com.darren.springbootmall.dao.OrderDao;
import com.darren.springbootmall.dao.ProductDao;
import com.darren.springbootmall.dao.UserDao;
import com.darren.springbootmall.dto.BuyItem;
import com.darren.springbootmall.dto.CreateOrderRequest;
import com.darren.springbootmall.pojo.Order;
import com.darren.springbootmall.pojo.OrderItem;
import com.darren.springbootmall.pojo.Product;
import com.darren.springbootmall.pojo.User;
import com.darren.springbootmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        User user = userDao.getUserByUserId(userId);

        if (user == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"userId不存在");
        }

        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();
        for(BuyItem buyItem : createOrderRequest.getBuyItemList()){
            Product product = productDao.getProductById(buyItem.getProductId());
            //檢查庫存
            if(product == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"商品不存在");
            }else if(product.getStock()< buyItem.getQuantity()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"庫存數不足，無法購買");
            }
            //扣除商品庫存
            productDao.updateStock(product.getProductId(),product.getStock()-buyItem.getQuantity());





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

        //創建訂單
        Integer orderId =  orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItem(orderId,orderItemList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }
}
