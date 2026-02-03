package com.practice.sprintthree_productinventory.controller;

import com.practice.sprintthree_productinventory.dto.request.ProductCreateRequest;
import com.practice.sprintthree_productinventory.dto.response.ApiResponse;
import com.practice.sprintthree_productinventory.dto.response.ProductResponse;
import com.practice.sprintthree_productinventory.service.ProductService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(
            @Valid @RequestBody ProductCreateRequest request){
        ProductResponse productResponse = productService.createProduct(request);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Product created successfully");
        apiResponse.setData(productResponse);
        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts(
            @RequestParam(required = false) String category){
        List<ProductResponse> productResponse = productService.getAllProducts(category);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("All products obtained successfully");
        apiResponse.setData(productResponse);
        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProduct(
            @PathVariable Long id){
        ProductResponse productResponse = productService.getProduct(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Product found successfully");
        apiResponse.setData(productResponse);
        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }
}
