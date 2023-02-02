package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Repository.ProductRepository;
import com.astroitsolutions.clienttracker.Utils.TestUtils;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Autowired
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    TestUtils clientTestUtils = new TestUtils();

    @Test
    public void addProduct_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.save(mockproduct)).thenReturn(mockproduct);

        Product addedProduct = productService.addProduct(mockproduct);

        assertNotNull(addedProduct);
        assertEquals(addedProduct, mockproduct);
    }
    
    @Test
    public void findProductById_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(mockproduct));

        Product p = productService.findProductById(mockproduct.getId());

        assertNotNull(p);
        assertEquals(p, mockproduct);
    }

    @Test
    public void findProductByName_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findByName(any())).thenReturn(Optional.of(mockproduct));

        Product p = productService.findProductByName(mockproduct.getName());

        assertNotNull(p);
        assertEquals(p, mockproduct);
    }

    @Test
    public void findProductById_null_noProductFoundById(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findById(any())).thenReturn(Optional.empty());

        Product p = productService.findProductById(mockproduct.getId());

        assertNull(p);
    }

    @Test
    public void findProductByName_null_noProductFoundByName(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findByName(any())).thenReturn(Optional.empty());

        Product p = productService.findProductByName(mockproduct.getName());

        assertNull(p);
    }

    @Test
    public void retrieveAllReviewsForProductById_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findById(mockproduct.getId())).thenReturn(Optional.of(mockproduct));

        List<Review> reviewsList = productService.retrieveAllReviewsForProductById(mockproduct.getId());

        assertNotNull(reviewsList);
        assertEquals(reviewsList, mockproduct.getProductReviews());
    }

    @Test
    public void retrieveAllReviewsForProductByName_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findByName(mockproduct.getName())).thenReturn(Optional.of(mockproduct));

        List<Review> reviewsList = productService.retrieveAllReviewsForProductByName(mockproduct.getName());

        assertNotNull(reviewsList);
        assertEquals(reviewsList, mockproduct.getProductReviews());
    }

    @Test
    public void retrieveAllReviewsForProductById_null_noProductFoundById(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findById(mockproduct.getId())).thenReturn(Optional.empty());

        List<Review> reviewsList = productService.retrieveAllReviewsForProductById(mockproduct.getId());

        assertNull(reviewsList);
    }

    @Test
    public void retrieveAllReviewsForProductByName_null_noProductFoundByName(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findByName(mockproduct.getName())).thenReturn(Optional.empty());

        List<Review> reviewsList = productService.retrieveAllReviewsForProductByName(mockproduct.getName());

        assertNull(reviewsList);
    }

    @Test
    public void activateProductById_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(mockproduct));

        Product retreivedProduct = productService.activateProductById(mockproduct.getId());

        assertNotNull(retreivedProduct);
        assertEquals(mockproduct, retreivedProduct);
    }

    @Test
    public void deactivateProductById_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(mockproduct));

        Product retreivedProduct = productService.deactivateProductById(mockproduct.getId());

        assertNotNull(retreivedProduct);
        assertEquals(mockproduct, retreivedProduct);
    }

    @Test
    public void activateProductByName_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findByName(anyString())).thenReturn(Optional.of(mockproduct));

        Product retreivedProduct = productService.activateProductByName(mockproduct.getName());

        assertNotNull(retreivedProduct);
        assertEquals(mockproduct, retreivedProduct);
    }

    @Test
    public void deactivateProductByName_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findByName(anyString())).thenReturn(Optional.of(mockproduct));

        Product retreivedProduct = productService.deactivateProductByName(mockproduct.getName());

        assertNotNull(retreivedProduct);
        assertEquals(mockproduct, retreivedProduct);
    }

    @Test
    public void activateProductById_productNotFound(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        Product retreivedProduct = productService.activateProductById(mockproduct.getId());

        assertNull(retreivedProduct);
    }

    @Test
    public void deactivateProductById_productNotFound(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        Product retreivedProduct = productService.deactivateProductById(mockproduct.getId());

        assertNull(retreivedProduct);
    }

    @Test
    public void activateProductByName_productNotFound(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findByName(anyString())).thenReturn(Optional.empty());

        Product retreivedProduct = productService.activateProductByName(mockproduct.getName());

        assertNull(retreivedProduct);
    }

    @Test
    public void deactivateProductByName_productNotFound(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findByName(anyString())).thenReturn(Optional.empty());

        Product retreivedProduct = productService.deactivateProductByName(mockproduct.getName());

        assertNull(retreivedProduct);
    }
}
