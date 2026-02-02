package com.astroitsolutions.clienttracker.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.astroitsolutions.clienttracker.Entity.Review;
import com.astroitsolutions.clienttracker.Repository.ReviewRepository;

@Component
public class ReviewDao {
    private ReviewRepository reviewRepository;

    public Optional<List<Review>> findAllByProductId(int productId, Pageable pageable){
        return reviewRepository.findAllByProductId(productId, pageable);
    }

    public Optional<List<Review>> findAllByClientId(int productId, Pageable pageable){
        return reviewRepository.findAllByClientId(productId, pageable);
    }
}
