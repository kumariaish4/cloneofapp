package com.AmazonClone.controller;

import com.AmazonClone.payload.OrderItemDto;
import com.AmazonClone.service.OrderItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(@RequestBody OrderItemDto orderItemDto) {
        OrderItemDto createdOrderItem = orderItemService.createOrderItem(orderItemDto);
        return new ResponseEntity<>(createdOrderItem,HttpStatus.CREATED);
    }

    @PutMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDto> updateOrderItem(@PathVariable int orderItemId, @RequestBody OrderItemDto orderItemDto) {
        OrderItemDto updatedOrderItem = orderItemService.updateOrderItem(orderItemId, orderItemDto);
        return new ResponseEntity<>(updatedOrderItem,HttpStatus.OK);
    }

    @DeleteMapping("/{orderItemId}")
    public void deleteOrderItem(@PathVariable int orderItemId) {
        orderItemService.deleteOrderItem(orderItemId);
    }

    @GetMapping
    public List<OrderItemDto> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{orderItemId}")
    public ResponseEntity<OrderItemDto> getOrderItemById(@PathVariable int orderItemId) {
        return new ResponseEntity<>(orderItemService.getOrderItemById(orderItemId),HttpStatus.OK);
    }
}

