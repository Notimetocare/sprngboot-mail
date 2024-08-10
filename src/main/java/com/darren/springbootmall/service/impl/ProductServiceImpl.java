package com.darren.springbootmall.service.impl;

import com.darren.springbootmall.constant.ProductCategory;
import com.darren.springbootmall.dao.ProductDao;
import com.darren.springbootmall.dto.ProductQueryParams;
import com.darren.springbootmall.dto.ProductRequest;
import com.darren.springbootmall.pojo.Product;
import com.darren.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao ProductDao;

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        return ProductDao.getProducts(productQueryParams);
    }

    @Override
    public Product getProductById(Integer productId) {

        return ProductDao.getProductById(productId);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return ProductDao.createProduct(productRequest);
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        ProductDao.updateProduct(productId, productRequest);
        }

    @Override
    public void deleteProductById(Integer productId) {
        ProductDao.deleteProductById(productId);
    }

    @Override
    public Integer countProducts(ProductQueryParams productQueryParams) {

        return ProductDao.countProducts(productQueryParams);
    }
}
