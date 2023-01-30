package com.astroitsolutions.clienttracker.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @GeneratedValue
    private int id;

    private Double price;

    private String name;

    private String description;
    
    @JsonProperty(access = Access.READ_ONLY)
    private int rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonProperty(access = Access.READ_ONLY)
    @ToString.Exclude
    private Transaction transaction;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonProperty(access = Access.READ_ONLY)
    @JsonBackReference
    private List<Review> productReviews = new ArrayList<>();

    // @Override
    // public String toString(){
    //     return "{"+
    //         "'id' : " + id +
    //         "'name' : '" + name + "'" +
    //         "'description' : '" + description + "'" +
    //         "'rating' : " + rating +
    //      "}";
    // }
}
