package com.AmazonClone.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="cartItem")

public class CartItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @Column(name="product_id")
//    private int productId;
//    @Column(name="quality")
//    private int quantity;
//    @Column(name="price")
//    private double price;
//    @ManyToOne
//    @JoinColumn(name = "cart_id", insertable = false, updatable = false)
//    private Cart cart;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id", insertable = false, updatable = false)
//    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id",insertable = false, updatable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",insertable = false, updatable = false)
    private Product product;
    @Column(name="quality")
    private int quantity;
    @Column(name="price")
    private double price;
    @Column(name="product_id")
   private int productId;
    @Column(name="cart_id")
    private int cartId;

}
