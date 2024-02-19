package com.example.NeobisShopProject;
import com.example.NeobisShopProject.controller.ProductController;
import com.example.NeobisShopProject.dto.ProductDto;
import com.example.NeobisShopProject.service.Impl.ProductServiceImpl;
import com.example.NeobisShopProject.utils.ProductErrorResponse;
import com.example.NeobisShopProject.utils.ProductNotCreatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductServiceImpl productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        when(productService.createProduct(productDto)).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.createProduct(productDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDto, response.getBody());
        verify(productService, times(1)).createProduct(productDto);
    }

    @Test
    void testGetAllProducts() {
        List<ProductDto> productList = new ArrayList<>();
        when(productService.getAllProducts()).thenReturn(productList);

        ResponseEntity<List<ProductDto>> response = productController.getAllProducts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testGetProductById() {
        Long productId = 1L;
        ProductDto productDto = new ProductDto();
        when(productService.getProductById(productId)).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDto, response.getBody());
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        ProductDto productDto = new ProductDto();
        when(productService.updateProduct(productId, productDto)).thenReturn(productDto);

        ResponseEntity<ProductDto> response = productController.updateProduct(productId, productDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDto, response.getBody());
        verify(productService, times(1)).updateProduct(productId, productDto);
    }

    @Test
    void testDeleteProduct() {
        Long productId = 1L;

        ResponseEntity<Void> response = productController.deleteProduct(productId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    void testHandleException() {
        ProductNotCreatedException exception = new ProductNotCreatedException("Product creation failed");
        ResponseEntity<ProductErrorResponse> responseEntity = productController.handleException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Product creation failed", responseEntity.getBody());
        // You may verify more details based on your specific implementation
    }
}
