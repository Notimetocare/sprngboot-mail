package com.darren.springbootmall.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NotEmpty
public class CreateOrderRequest {
    private List<BuyItem> buyItemList;

}
