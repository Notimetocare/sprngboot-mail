package com.darren.springbootmall.dto;

import com.darren.springbootmall.constant.ProductCategory;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data

public class ProductRequest {
    @NotNull
    private String productName;
    @NotNull
    private ProductCategory category;
    @NotNull
    private String imageUrl;
    @NotNull
    private Double price;
    @NotNull
    private Integer stock;
    @NotNull
    private String description;
}
