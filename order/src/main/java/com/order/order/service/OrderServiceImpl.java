package com.order.order.service;
import com.order.order.repository.OrderRepository;
import com.order.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository theOrderRepository) {
        orderRepository = theOrderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(int theId) {
        Optional<Order> result = orderRepository.findById(theId);
        Order theOrder = null;

        if (result.isPresent()) {
            theOrder = result.get();
        } else {
            throw new RuntimeException("Order with id: " + theId + "not found");
        }

        return theOrder;
    }

    @Override
    public Order save(Order theOrder) {
        return orderRepository.save(theOrder);
    }

    @Override
    public void delete(int theId) {
        orderRepository.deleteById(theId);
    }
}