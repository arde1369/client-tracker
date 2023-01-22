package com.astroitsolutions.clienttracker.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    @JsonProperty(access = Access.READ_ONLY)
    private int id;

    private Double price;

    private String description;
    
    private Rating rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonProperty(access = Access.READ_ONLY)
    private Transaction transaction;

    @OneToMany(mappedBy = "product")
    private List<Review> productReviews = new ArrayList<>();
}
