package com.darren.springbootmall.ProductMapper;

import com.darren.springbootmall.constant.ProductCategory;
import com.darren.springbootmall.pojo.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));

        String categorystr = rs.getString("category");
        ProductCategory category = ProductCategory.valueOf(categorystr);
        product.setCategory(category);


        product.setImageUrl(rs.getString("image_url"));
        product.setPrice(rs.getDouble("price"));
        product.setStock(rs.getInt("stock"));
        product.setDescription(rs.getString("description"));
        product.setCreatedDate(rs.getDate("created_date"));
        product.setLastModifiedDate(rs.getDate("last_modified_date"));
        return product;

    }
}
