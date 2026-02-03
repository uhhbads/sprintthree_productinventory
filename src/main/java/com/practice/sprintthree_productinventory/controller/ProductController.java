package com.practice.sprintthree_productinventory.controller;

import com.practice.sprintthree_productinventory.dto.request.ProductCreateRequest;
import com.practice.sprintthree_productinventory.dto.request.ProductUpdateRequest;
import com.practice.sprintthree_productinventory.dto.request.StockUpdateRequest;
import com.practice.sprintthree_productinventory.dto.response.ApiResponse;
import com.practice.sprintthree_productinventory.dto.response.ProductResponse;
import com.practice.sprintthree_productinventory.service.ProductService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
//@Tag(name = "Product API", description = "Operations related to products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    //@Operation(
    //        summary = "Create a new product",
    //        description = "Create a new product by providing name, description, price, stock quantity, and category. Returns the created product.",
    //        responses = {
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Product created successfully"),
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Validation error")
    //        }
    //)
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
    //@Operation(
    //        summary = "Get all products",
    //        description = "Retrieve all products. Optionally filter by category using the query parameter 'category'.",
    //        responses = {
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "List of products retrieved")
    //        }
    //)
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
    //@Operation(
    //        summary = "Get product by ID",
    //        description = "Retrieve a single product by its unique ID. Returns 404 if the product does not exist.",
    //        responses = {
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product found"),
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    //        }
    //)
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

    @PutMapping("/{id}")
    //@Operation(
    //        summary = "Update a product",
    //        description = "Update the product details by ID. Only non-null fields in the request will be updated.",
    //        responses = {
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product updated successfully"),
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    //        }
    //)
    public ResponseEntity<ApiResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest request){
        ProductResponse productResponse = productService.updateProduct(id, request);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Product updated successfully");
        apiResponse.setData(productResponse);
        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }

    @DeleteMapping("/{id}")
    //@Operation(
    //        summary = "Delete a product",
    //        description = "Delete a product by its ID. Returns 404 if the product does not exist.",
    //        responses = {
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product deleted successfully"),
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    //        }
    //)
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Product deleted successfully");
        apiResponse.setData(null);
        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }

    @PatchMapping("/{id}/stock")
    //@Operation(
    //        summary = "Update product stock",
    //        description = "Increase or decrease the stock quantity of a product. Use the 'operation' field in the request body as ADD or REMOVE.",
    //        responses = {
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product stock updated successfully"),
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Insufficient stock or validation error"),
    //                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found")
    //        }
    //)
    public ResponseEntity<ApiResponse> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody StockUpdateRequest request){
        ProductResponse productResponse = productService.updateStock(id, request);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Product stock updated successfully");
        apiResponse.setData(productResponse);
        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(apiResponse);
    }
}
