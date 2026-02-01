package com.practice.sprintthree_productinventory.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateRequest {
    private String name;

    private String description;

    @Min(value = 0, message = "Price must be positive")
    private Double price;

    @Min(value = 0, message = "Stock quantity must be positive")
    private Integer stockQuantity;

    private String category;
}
