package com.darren.springbootmall.dao;

import com.darren.springbootmall.constant.ProductCategory;
import com.darren.springbootmall.dto.ProductQueryParams;
import com.darren.springbootmall.dto.ProductRequest;
import com.darren.springbootmall.pojo.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(ProductQueryParams productQueryParams);
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    Integer countProducts(ProductQueryParams productQueryParams);
}
