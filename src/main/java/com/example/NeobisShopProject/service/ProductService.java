package com.example.NeobisShopProject.service;

import com.example.NeobisShopProject.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public ProductDto createProduct(ProductDto productDto);
    public List<ProductDto> getAllProducts();
    public ProductDto getProductById(Long productId);
    public ProductDto updateProduct(Long productId, ProductDto productDto);
    public void deleteProduct(Long productId) ;
}


