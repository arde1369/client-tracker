package com.astroitsolutions.clienttracker.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astroitsolutions.clienttracker.Entity.Product;
import com.astroitsolutions.clienttracker.Entity.Review;

@RestController
@RequestMapping("/api/product")
public class ProductControllerImpl implements ProductController {

    @Override
    @PostMapping()
    public Product addProduct(Product product) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public Product findProductById(@PathVariable int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("/{name}")
    public Product findProductByName(@PathVariable String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("/reviews/{id}")
    public List<Review> retrieveAllReviewsForProductById(@PathVariable int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @GetMapping("/reviews/{name}")
    public List<Review> retrieveAllReviewsForProductByName(@PathVariable String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @DeleteMapping("/delete/{name}")
    public void deleteProductById(@PathVariable int id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    @DeleteMapping("/delete/{name}")
    public void deleteProductByName(@PathVariable String name) {
        // TODO Auto-generated method stub
        
    }
    
}
