package com.darren.springbootmall.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NotNull
@NotBlank
@Data
public class BuyItem {
    private Integer productId;
    private Integer quantity;

}
