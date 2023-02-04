package com.astroitsolutions.clienttracker.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Service.ProductService;
import com.astroitsolutions.clienttracker.Utils.TestUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Autowired
    @InjectMocks
    private ProductControllerImpl productControllerImpl;

    @Mock
    private ProductService productService;

    TestUtils testUtils = new TestUtils();

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void addProduct_Success_201() throws JsonProcessingException, Exception{
        Product mockProduct = testUtils.createSingleProduct();
        
        when(productService.addProduct(any(Product.class))).thenReturn(mockProduct);

        ResponseEntity<Product> responseEntity = productControllerImpl.addProduct(mockProduct);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(201), responseEntity.getStatusCode());
        assertEquals(mockProduct, responseEntity.getBody());
    }

    @Test
    public void addClient_failure_500() throws JsonProcessingException, Exception{
        Product mockProduct = testUtils.createSingleProduct();
        
        when(productService.addProduct(any(Product.class))).thenThrow(new NullPointerException());

        ResponseEntity<Product> responseEntity = productControllerImpl.addProduct(mockProduct);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void findProductById_success_200(){
        Product mockProduct = testUtils.createSingleProduct();
        
        when(productService.findProductById(anyInt())).thenReturn(mockProduct);

        ResponseEntity<Product> responseEntity = productControllerImpl.findProductById(mockProduct.getId());

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(mockProduct, responseEntity.getBody());
    }

    @Test
    public void findProductById_failure_400(){
        Product mockProduct = testUtils.createSingleProduct();
        
        when(productService.findProductById(anyInt())).thenReturn(null);

        ResponseEntity<Product> responseEntity = productControllerImpl.findProductById(mockProduct.getId());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void findProductById__failure_500(){
        Product mockProduct = testUtils.createSingleProduct();
        
        when(productService.findProductById(anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<Product> responseEntity = productControllerImpl.findProductById(mockProduct.getId());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void findProductByName_success_200(){
        Product mockProduct = testUtils.createSingleProduct();
        
        when(productService.findProductByName(anyString())).thenReturn(mockProduct);

        ResponseEntity<Product> responseEntity = productControllerImpl.findProductByName(mockProduct.getName());

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(mockProduct, responseEntity.getBody());
    }

    @Test
    public void findProductByName_failure_400(){
        Product mockProduct = testUtils.createSingleProduct();
        
        when(productService.findProductByName(anyString())).thenReturn(null);

        ResponseEntity<Product> responseEntity = productControllerImpl.findProductByName(mockProduct.getName());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void findProductByName__failure_500(){
        Product mockProduct = testUtils.createSingleProduct();
        
        when(productService.findProductByName(anyString())).thenThrow(new NullPointerException());

        ResponseEntity<Product> responseEntity = productControllerImpl.findProductByName(mockProduct.getName());

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void retrieveAllReviewsForProductById_success_200(){
        List<Review> retrievedProductReviews = testUtils.createReviewsList(testUtils.createSingleProduct(), testUtils.createNewCompleteClient());

        when(productService.retrieveAllReviewsForProductById(anyInt())).thenReturn(retrievedProductReviews);

        ResponseEntity<List<Review>> responseEntity = productControllerImpl.retrieveAllReviewsForProductById(0);

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(retrievedProductReviews, responseEntity.getBody());
    }

    @Test
    public void retrieveAllReviewsForProductById_failure_400(){
        when(productService.retrieveAllReviewsForProductById(anyInt())).thenReturn(null);

        ResponseEntity<List<Review>> responseEntity = productControllerImpl.retrieveAllReviewsForProductById(0);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }
    

    @Test
    public void retrieveAllReviewsForProductById_failure_500(){
        when(productService.retrieveAllReviewsForProductById(anyInt())).thenThrow(new NullPointerException());

        ResponseEntity<List<Review>> responseEntity = productControllerImpl.retrieveAllReviewsForProductById(0);

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void retrieveAllReviewsForProductByName_success_200(){
        List<Review> retrievedProductReviews = testUtils.createReviewsList(testUtils.createSingleProduct(), testUtils.createNewCompleteClient());

        when(productService.retrieveAllReviewsForProductByName(anyString())).thenReturn(retrievedProductReviews);

        ResponseEntity<List<Review>> responseEntity = productControllerImpl.retrieveAllReviewsForProductByName("test");

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(200), responseEntity.getStatusCode());
        assertEquals(retrievedProductReviews, responseEntity.getBody());
    }

    @Test
    public void retrieveAllReviewsForProductByName_failure_400(){
        when(productService.retrieveAllReviewsForProductByName(anyString())).thenReturn(null);

        ResponseEntity<List<Review>> responseEntity = productControllerImpl.retrieveAllReviewsForProductByName("test");

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(400), responseEntity.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }

    @Test
    public void retrieveAllReviewsForProductByName_failure_500(){
        when(productService.retrieveAllReviewsForProductByName(anyString())).thenThrow(new NullPointerException());

        ResponseEntity<List<Review>> responseEntity = productControllerImpl.retrieveAllReviewsForProductByName("test");

        assertNotNull(responseEntity);
        assertNull(responseEntity.getBody());
        assertEquals(HttpStatusCode.valueOf(500), responseEntity.getStatusCode());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), responseEntity.getHeaders().get("error-message").get(0));
    }
}
