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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
    @Column(name="user_id")
        private int userId;

        @OneToMany(mappedBy = "cart")
        private List<CartItem> cartItems;

        @OneToOne
        @JoinColumn(name = "user_id", insertable = false, updatable = false)
        private User user;


}
