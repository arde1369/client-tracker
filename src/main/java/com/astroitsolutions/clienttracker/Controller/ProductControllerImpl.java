package com.astroitsolutions.clienttracker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Service.ProductService;

import jakarta.annotation.Nonnull;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductControllerImpl implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    @PostMapping()
    public ResponseEntity<Product> addProduct(@Nonnull @RequestBody Product product) {
        Product addedProduct = null;
        try{
            addedProduct = productService.addProduct(product);
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(addedProduct);
    }

    @Override
    @GetMapping("/id")
    public ResponseEntity<Product> findProductById(@RequestParam int id) {
        Product retrievedProduct = null;
        try{
            retrievedProduct = productService.findProductById(id);
            if(retrievedProduct == null){
                log.error("Unable to find product by id - " + id);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedProduct);
    }

    @Override
    @GetMapping("/name")
    public ResponseEntity<Product> findProductByName(@RequestParam String name) {
        Product retrievedProduct = null;
        try{
            retrievedProduct = productService.findProductByName(name);
            if(retrievedProduct == null){
                log.error("Unable to Retrieve product by name - " + name);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedProduct);
    }

    @Override
    @GetMapping("/reviews/id")
    public ResponseEntity<List<Review>> retrieveAllReviewsForProductById(@RequestParam int id) {
        List<Review> retrievedProductReviews = null;
        try{
            retrievedProductReviews = productService.retrieveAllReviewsForProductById(id);
            if(retrievedProductReviews == null){
                log.error("Unable to find product by id - " + id);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedProductReviews);
    }

    @Override
    @GetMapping("/reviews/name")
    public ResponseEntity<List<Review>> retrieveAllReviewsForProductByName(@RequestParam String name) {
        List<Review> retrievedProductReviews = null;
        try{
            retrievedProductReviews = productService.retrieveAllReviewsForProductByName(name);
            if(retrievedProductReviews == null){
                log.error("Unable to find product by id - " + name);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedProductReviews);
    }

    @Override
    @PutMapping("/activate/id")
    public ResponseEntity<Product> activateProductById(@RequestParam int id) {
        Product retrievedProduct = null;
        try{
            retrievedProduct = productService.activateProductById(id);
            if(retrievedProduct == null){
                log.error("Unable to activate product by id - " + id);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedProduct);
    }

    @Override
    @PutMapping("/deactivate/id")
    public ResponseEntity<Product> deactivateProductById(@RequestParam int id) {
        Product retrievedProduct = null;
        try{
            retrievedProduct = productService.deactivateProductById(id);
            if(retrievedProduct == null){
                log.error("Unable to deactivate product by id - " + id);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedProduct);
    }

    @Override
    @PutMapping("/activate/name")
    public ResponseEntity<Product> activateProductByName(@RequestParam String name) {
        Product retrievedProduct = null;
        try{
            retrievedProduct = productService.activateProductByName(name);
            if(retrievedProduct == null){
                log.error("Unable to activate product by name - " + name);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedProduct);
    }

    @Override
    @PutMapping("/deactivate/name")
    public ResponseEntity<Product> deactivateProductByName(@RequestParam String name) {
        Product retrievedProduct = null;
        try{
            retrievedProduct = productService.deactivateProductByName(name);
            if(retrievedProduct == null){
                log.error("Unable to deactivate product by name - " + name);
                return ResponseEntity
                .badRequest()
                .header("error-message", HttpStatus.NOT_FOUND.getReasonPhrase())
                .body(null);
            }
        } catch(Exception ex){
            log.error("Unexpected error occurred - ", ex);
                return ResponseEntity
                .internalServerError()
                .header("error-message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .body(null);
        }
        return ResponseEntity.ok(retrievedProduct);
    }

    // @Override
    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<HttpStatus> deleteProductById(@PathVariable int id) {
    //     productService.deleteProductById(id);
    //     return ResponseEntity.ok(HttpStatus.OK);
    // }

    // @Override
    // @DeleteMapping("/delete/{name}")
    // public ResponseEntity<HttpStatus> deleteProductByName(@PathVariable String name) {
    //     productService.deleteProductByName(name);
    //     return ResponseEntity.ok(HttpStatus.OK);
    // }
}
