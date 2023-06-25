package com.AmazonClone.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    private int id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private String orderDate;
    private String status;
    private double totalAmount;
    private Integer userId;

}
