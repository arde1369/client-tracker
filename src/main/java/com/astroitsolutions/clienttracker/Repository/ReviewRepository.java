package com.astroitsolutions.clienttracker.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.astroitsolutions.clienttracker.Entity.Review;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    public Optional<List<Review>> findAllByProductId(int productId, Pageable pageable);

    public Optional<List<Review>> findAllByClientId(int productId, Pageable pageable);

}
