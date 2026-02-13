package com.astroitsolutions.clienttracker.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.astroitsolutions.data_services.Dao.ProductDao;
import com.astroitsolutions.data_services.Dao.ReviewDao;
import com.astroitsolutions.data_services.Entity.Product;
import com.astroitsolutions.data_services.Entity.Review;
import com.astroitsolutions.clienttracker.Utils.TestUtils;


@SpringBootTest
public class ProductServiceTest {


    @Autowired
    private ProductService productService;

    @MockBean
    private ProductDao productRepository;

    @MockBean
    private ReviewDao reviewRepository;

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

        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.of(mockproduct));

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

        Mockito.when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        Product p = productService.findProductById(mockproduct.getId());

        assertNull(p);
    }

    @Test
    public void findProductByName_null_noProductFoundByName(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(productRepository.findByName(anyString())).thenReturn(Optional.empty());

        Product p = productService.findProductByName(mockproduct.getName());

        assertNull(p);
    }

    @Test
    public void retrieveAllReviewsForProductById_success(){
        Product mockproduct = clientTestUtils.createSingleProduct();

        Mockito.when(reviewRepository.findAllByProductId(anyInt(), any(Pageable.class))).thenReturn(Optional.of(mockproduct.getProductReviews()));

        List<Review> reviewsList = productService.retrieveAllReviewsForProductById(mockproduct.getId(), 1, 1);

        assertNotNull(reviewsList);
        assertEquals(reviewsList, mockproduct.getProductReviews());
    }

    @Test
    public void retrieveAllReviewsForProductById_empty(){

        Mockito.when(reviewRepository.findAllByProductId(anyInt(), any(Pageable.class))).thenReturn(Optional.empty());

        List<Review> reviewsList = productService.retrieveAllReviewsForProductById(1, 1, 1);

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
