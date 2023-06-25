package com.AmazonClone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orderItem")

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="orderEntity_id")
    private int orderEntityId;
    @Column(name="product_id")
    private int productId;
    @Column(name="quantity")
    private int quantity;
    @Column(name="price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "orderEntity_id", insertable = false, updatable = false)
    private OrderEntity orderEntity;
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
}
