package com.practice.sprintthree_productinventory.repository;

import com.practice.sprintthree_productinventory.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProductRepository {
    private final Map<Long, Product> store = new ConcurrentHashMap<>();

    public Product save(Product product){
        store.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }

    public List<Product> findAll(){
        return List.copyOf(store.values());
    }

    public void delete(Long id){
        store.remove(id);
    }

    public List<Product> findByCategory(String category){
        return store.values()
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}
