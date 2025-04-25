package com.guarani.teste_backend.domain.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.guarani.teste_backend.domain.dto.ProductDTO;
import com.guarani.teste_backend.domain.entity.Product;
import com.guarani.teste_backend.domain.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(ProductDTO dto){
        Product product = Product.builder()
                .id(dto.id())
                .name(dto.name())
                .category(dto.category())
                .price(dto.price())
                .build();

        return productRepository.save(product);
    }


    @Transactional
    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> filterByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filterByCategory(String category){
        return productRepository.findByCategoryContainingIgnoreCase(category);
    }

    public List<Product> filterByPriceRange(BigDecimal min, BigDecimal max){
        return productRepository.findByPriceBetween(min, max);
    }
}
