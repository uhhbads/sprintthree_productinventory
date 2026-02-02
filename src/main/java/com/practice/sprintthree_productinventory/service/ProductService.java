package com.practice.sprintthree_productinventory.service;

import com.practice.sprintthree_productinventory.dto.request.ProductCreateRequest;
import com.practice.sprintthree_productinventory.dto.request.ProductUpdateRequest;
import com.practice.sprintthree_productinventory.dto.response.ProductResponse;
import com.practice.sprintthree_productinventory.exception.ProductNotFoundException;
import com.practice.sprintthree_productinventory.model.Product;
import com.practice.sprintthree_productinventory.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductCreateRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setCategory(request.getCategory());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        LocalDateTime now = LocalDateTime.now();
        product.setCreatedAt(now);
        product.setUpdatedAt(now);

        return mapToResponse(productRepository.save(product));
    }

    public ProductResponse updateProduct(Long id, ProductUpdateRequest request){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        if(request.getName() != null){
            product.setName(request.getName());
        }

        if(request.getDescription() != null){
            product.setDescription(request.getDescription());
        }

        if(request.getPrice() != null){
            product.setPrice(request.getPrice());
        }

        if(request.getStockQuantity() != null){
            product.setStockQuantity(request.getStockQuantity());
        }

        if(request.getCategory() != null){
            product.setCategory(request.getCategory());
        }

        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);

        return mapToResponse(product);
    }

    public String deleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        productRepository.delete(product.getId());

        return "Product with id " + id + " deleted successfully";
    }

    public ProductResponse getProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return mapToResponse(product);
    }

    private ProductResponse mapToResponse(Product product){
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategory(product.getCategory());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCreatedAt(product.getCreatedAt());

        int qty = product.getStockQuantity() != null ? product.getStockQuantity() : 0;
        String stockStatus;
        if (qty == 0) stockStatus = "OUT_OF_STOCK";
        else if (qty < 5) stockStatus = "LOW_STOCK";
        else stockStatus = "IN_STOCK";
        response.setStockStatus(stockStatus);

        return response;
    }
}
