package com.AmazonClone.service;

import com.AmazonClone.payload.OrderDto;

import java.util.List;

public interface OrderEntityService {
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getAllOrder();
    OrderDto getOrderById(int id);
    OrderDto updateOrder(int id,OrderDto orderDto);
    void deleteOrder(int id);
}
