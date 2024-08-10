package com.darren.springbootmall.controller;

import com.darren.springbootmall.constant.ProductCategory;
import com.darren.springbootmall.dto.ProductQueryParams;
import com.darren.springbootmall.dto.ProductRequest;
import com.darren.springbootmall.pojo.Product;
import com.darren.springbootmall.service.ProductService;
import com.darren.springbootmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("products")
    public ResponseEntity<Product> createProduct(@RequestBody @Validated ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Validated ProductRequest productRequest) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            productService.updateProduct(productId, productRequest);
            Product updatedProduct = productService.getProductById(productId);
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);

        }

    }

    @DeleteMapping("products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("products")

    public ResponseEntity<Page<Product>> getProducts(
            //filter
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            //sroting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,

            //分頁 PAGINATION
            @RequestParam(defaultValue = "5")@Max(100)@Min(0) Integer limit,
            @RequestParam(defaultValue = "0")@Min(0) Integer offset)
    {

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);
        //取得 productList
        List<Product> productList = productService.getProducts(productQueryParams);
        //取得總數量
        Integer total = productService.countProducts(productQueryParams);
        //分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResult(productList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}