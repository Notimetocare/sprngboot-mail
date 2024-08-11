package com.darren.springbootmall.dao.impl;

import com.darren.springbootmall.ProductMapper.OrderItemMapper;
import com.darren.springbootmall.ProductMapper.OrderRowMapper;
import com.darren.springbootmall.dao.OrderDao;
import com.darren.springbootmall.pojo.Order;
import com.darren.springbootmall.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer createOrder(Integer userId, int totalAmount) {
        String sql = "insert into `order`(user_id, total_amount, created_date, last_modified_date) values(:userId, :totalAmount, :createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("totalAmount",totalAmount);

        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);


        KeyHolder key = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),key);
        int orderId = key.getKey().intValue();
        return orderId;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Integer orderId) {
        String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, p.product_name, p.image_url\n" +
                "from order_item as oi left join product as p on oi.product_id = p.product_id where oi.order_id = :orderId";
        Map<String, Object> map = new HashMap<>();
        map.put("orderId",orderId);

        List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql,map,new OrderItemMapper());

        return orderItemList;
    }

    @Override
    public void createOrderItem(Integer orderId, List<OrderItem> orderItemList) {
        String sql = "INSERT INTO `order_item`(order_id, product_id, quantity, amount)values(:orderId, :productId, :quantity, :amount)";

        MapSqlParameterSource[] parameterSource = new MapSqlParameterSource[orderItemList.size()];

        for (int i = 0; i < orderItemList.size(); i++) {
            OrderItem orderItem = orderItemList.get(i);
            parameterSource[i]  = new MapSqlParameterSource();
            parameterSource[i].addValue("orderId",orderId);
            parameterSource[i].addValue("productId",orderItem.getProductId());
            parameterSource[i].addValue("quantity",orderItem.getQuantity());
            parameterSource[i].addValue("amount",orderItem.getAmount());

        }
        namedParameterJdbcTemplate.batchUpdate(sql,parameterSource);

    }

    @Override
    public Order getOrderById(Integer orderId) {
        String sql = "Select order_id, user_id, total_amount, created_date, last_modified_date from `order` where order_id = :orderId";

        Map<String, Object> map = new HashMap<>();
        map.put("orderId",orderId);

        List<Order> orderList = namedParameterJdbcTemplate.query(sql,map,new OrderRowMapper());

        if(orderList.size()>0){
            return  orderList.get(0);
        }else{
            return null;
        }
    }
}
