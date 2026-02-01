package com.practice.sprintthree_productinventory.dto.request;

import com.practice.sprintthree_productinventory.model.StockOperation;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockUpdateRequest {
    @NotNull
    private Integer quantity;

    @NotNull
    private StockOperation operation;
}
