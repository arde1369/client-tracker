package com.astroitsolutions.clienttracker.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private int id;

    @JsonProperty(access = Access.READ_ONLY)
    private Double price;

    @JsonProperty(access = Access.READ_ONLY)
    private String description;
    
    @JsonProperty(access = Access.READ_ONLY)
    private int rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonProperty(access = Access.READ_ONLY)
    private Transaction transaction;

    @OneToMany(mappedBy = "product")
    @JsonProperty(access = Access.READ_ONLY)
    @JsonBackReference
    private List<Review> productReviews = new ArrayList<>();
}
