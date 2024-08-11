package com.darren.springbootmall.pojo;


import lombok.Data;

import java.util.Date;
@Data
public class Order {
    private Integer orderId;
    private Integer userId;
    private int totalAmount;
    private Date createDate;
    private Date lastModifiedDate;

}
