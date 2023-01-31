package com.astroitsolutions.clienttracker.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString
public class Review {

    @Id
    @GeneratedValue
    @JsonProperty(access = Access.READ_ONLY)
    private int id;

    private int rating;

    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    @JsonProperty(access = Access.READ_ONLY)
    private Client client;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @ToString.Exclude
    private Product product;
}
