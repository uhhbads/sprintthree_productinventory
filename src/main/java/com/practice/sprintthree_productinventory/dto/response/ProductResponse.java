package com.practice.sprintthree_productinventory.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String category;
    private String stockStatus; // IN_STOCK, LOW_STOCK, OUT_OF_STOCK
    private LocalDateTime createdAt;
}
