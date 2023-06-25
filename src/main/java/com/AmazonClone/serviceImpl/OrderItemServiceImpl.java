package com.AmazonClone.serviceImpl;

import com.AmazonClone.entities.Category;
import com.AmazonClone.entities.OrderItem;
import com.AmazonClone.payload.OrderDto;
import com.AmazonClone.payload.OrderItemDto;
import com.AmazonClone.payload.ProductDto;
import com.AmazonClone.repository.OrderItemRepository;
import com.AmazonClone.service.OrderItemService;
import com.AmazonClone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
    }

    @Override
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderEntityId(orderItemDto.getOrderId());
        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());

        // Fetch the product to calculate the price
        ProductDto product = productService.getProductById(orderItemDto.getProductId());

        if (product == null) {
            throw new EntityNotFoundException("Product not found with ID: " + orderItemDto.getProductId());
        }

        double price = product.getPrice() * orderItemDto.getQuantity();
        orderItem.setPrice(price);

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return convertToDto(savedOrderItem);
    }

    @Override
    public OrderItemDto updateOrderItem(int orderItemId, OrderItemDto orderItemDto) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderItemId);

        if (!orderItemOptional.isPresent()) {
            throw new EntityNotFoundException("OrderItem not found with ID: " + orderItemId);
        }

        OrderItem orderItem = orderItemOptional.get();
        orderItem.setOrderEntityId(orderItemDto.getOrderId());
        orderItem.setProductId(orderItemDto.getProductId());
        orderItem.setQuantity(orderItemDto.getQuantity());

        // Fetch the product to calculate the price
        ProductDto product = productService.getProductById(orderItemDto.getProductId());

        if (product == null) {
            throw new EntityNotFoundException("Product not found with ID: " + orderItemDto.getProductId());
        }

        double price = product.getPrice() * orderItemDto.getQuantity();
        orderItem.setPrice(price);

        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);

        return convertToDto(updatedOrderItem);
    }

    @Override
    public void deleteOrderItem(int orderItemId) {
        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderItemId);

        if (!orderItemOptional.isPresent()) {
            throw new EntityNotFoundException("OrderItem not found with ID: " + orderItemId);
        }

        OrderItem orderItem = orderItemOptional.get();
        orderItemRepository.delete(orderItem);
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemDto getOrderItemById(int orderItemId) {
        OrderItem orderItem = getOrderItem(orderItemId);
        return convertToDto(orderItem);
    }

    private OrderItem getOrderItem(int orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem not found with ID: " + orderItemId));
    }



    private OrderItemDto convertToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setOrderId(orderItem.getOrderEntityId());
        orderItemDto.setProductId(orderItem.getProductId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getPrice());

        return orderItemDto;
    }
}
