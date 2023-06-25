package com.AmazonClone.controller;

import com.AmazonClone.payload.OrderDto;
import com.AmazonClone.repository.OrderRepository;
import com.AmazonClone.service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/orders")
public class OrderController {


    @Autowired
    public OrderEntityService orderEntityService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto){
        OrderDto saved = orderEntityService.createOrder(orderDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public List<OrderDto> getAllOrder(){
        List<OrderDto> allOrder = orderEntityService.getAllOrder();
        return allOrder;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name="id") int id){
        return new ResponseEntity<>(orderEntityService.getOrderById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable(name="id") int id,@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderEntityService.updateOrder(id,orderDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable(name="id") int id){
        orderEntityService.deleteOrder(id);
    }

}
