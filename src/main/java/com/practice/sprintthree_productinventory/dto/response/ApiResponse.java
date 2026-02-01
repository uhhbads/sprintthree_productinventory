package com.practice.sprintthree_productinventory.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object data;
    private LocalDateTime timestamp;
}
