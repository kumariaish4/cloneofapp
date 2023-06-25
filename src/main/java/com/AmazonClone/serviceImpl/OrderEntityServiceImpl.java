package com.AmazonClone.serviceImpl;

import com.AmazonClone.entities.OrderEntity;
import com.AmazonClone.payload.OrderDto;
import com.AmazonClone.repository.OrderRepository;
import com.AmazonClone.service.OrderEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderEntityServiceImpl implements OrderEntityService {

    @Autowired
   public OrderRepository orderRepository;


    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        OrderEntity orderEntity=new OrderEntity();

        orderEntity.setOrderDate(parseOrderDate(orderDto.getOrderDate()));
        orderEntity.setStatus(orderDto.getStatus());
        orderEntity.setTotalAmount(orderDto.getTotalAmount());
        orderEntity.setUserId(orderDto.getUserId());
        OrderEntity orderEntity1 = orderRepository.save(orderEntity);
        OrderDto dto=new OrderDto();
        dto.setId(orderEntity1.getId());
        dto.setOrderDate(formatOrderDate(orderEntity1.getOrderDate()));
        dto.setStatus(orderEntity1.getStatus());
        dto.setUserId(orderEntity1.getUserId());
        dto.setTotalAmount(orderEntity1.getTotalAmount());
        return dto;
    }

    @Override
    public List<OrderDto> getAllOrder() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream().map(orders1->mapToDto(orders1)).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(int id) {
        OrderEntity save = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id not found"));
        OrderDto dto=new OrderDto();
        dto.setId(save.getId());
        dto.setOrderDate(formatOrderDate(save.getOrderDate()));

        dto.setUserId(save.getUserId());
        dto.setStatus(save.getStatus());
        dto.setTotalAmount(save.getTotalAmount());

        return dto;
    }

    @Override
    public OrderDto updateOrder(int id, OrderDto orderDto) {
        OrderEntity save = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id not found"));
        save.setOrderDate(parseOrderDate(orderDto.getOrderDate()));
        save.setStatus(orderDto.getStatus());
        save.setTotalAmount(orderDto.getTotalAmount());
        save.setUserId(orderDto.getUserId());
        OrderEntity save1 = orderRepository.save(save);
        OrderDto dto=new OrderDto();
        dto.setId(save1.getId());
        dto.setOrderDate(formatOrderDate(save1.getOrderDate()));
        dto.setStatus(save1.getStatus());
        dto.setUserId(save1.getUserId());
        dto.setTotalAmount(save1.getTotalAmount());
        return dto;
    }

    @Override
    public void deleteOrder(int id) {
        OrderEntity saved = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id not found"));
        orderRepository.deleteById(id);
    }

    OrderDto mapToDto(OrderEntity orders1){
        OrderDto dto=new OrderDto();
        dto.setId(orders1.getId());
        dto.setOrderDate(formatOrderDate(orders1.getOrderDate()));
        dto.setStatus(orders1.getStatus());
        dto.setUserId(orders1.getUserId());
        dto.setTotalAmount(orders1.getTotalAmount());
        return dto;
    }
    private LocalDateTime parseOrderDate(String orderDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(orderDate, formatter).atStartOfDay();
    }

    private String formatOrderDate(LocalDateTime orderDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return orderDate.format(formatter);
    }
}
