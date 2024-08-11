package com.darren.springbootmall.pojo;


import com.darren.springbootmall.ProductMapper.OrderItemMapper;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer orderId;
    private Integer userId;
    private int totalAmount;
    private Date createdDate;
    private Date lastModifiedDate;

    private List<OrderItem> orderItemList;

}
