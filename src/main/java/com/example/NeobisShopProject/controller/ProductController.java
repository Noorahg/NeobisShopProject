package com.example.NeobisShopProject.controller;

import com.example.NeobisShopProject.dto.ProductDto;
import com.example.NeobisShopProject.service.Impl.ProductServiceImpl;
import com.example.NeobisShopProject.utils.ProductErrorResponse;
import com.example.NeobisShopProject.utils.ProductNotCreatedException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Donuts", description = "All endpoints for all users to query products")

@RestController
@RequestMapping("/auth")

public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @Autowired
    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping("/admin/createProduct")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto createdProduct = productServiceImpl.createProduct(productDto);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/allProducts")
    @PreAuthorize("hasRole('ROLE_USER')")

    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> products = productServiceImpl.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/getProductById/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto product = productServiceImpl.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
        ProductDto updatedProduct = productServiceImpl.updateProduct(productId, productDto);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")

    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productServiceImpl.deleteProduct(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse>handleException(ProductNotCreatedException e){
        ProductErrorResponse response = new ProductErrorResponse(
            e.getMessage(),
        System.currentTimeMillis());
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }




}
