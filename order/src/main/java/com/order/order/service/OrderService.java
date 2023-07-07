package com.order.order.service;
import com.order.order.entity.Order;
import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order findById(int theId);

    Order save(Order theOrder);

    void delete(int theId);

}