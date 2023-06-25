package com.AmazonClone.service;

import com.AmazonClone.payload.OrderDto;
import com.AmazonClone.payload.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    OrderItemDto createOrderItem(OrderItemDto orderItemDto);
    List<OrderItemDto> getAllOrderItems();
    OrderItemDto getOrderItemById(int id);
    OrderItemDto updateOrderItem(int id,OrderItemDto orderItemDto);
    void deleteOrderItem(int id);

}
