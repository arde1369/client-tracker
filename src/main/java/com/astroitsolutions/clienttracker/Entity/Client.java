package com.astroitsolutions.clienttracker.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
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
public class Client {
    
    @Id
    @GeneratedValue
    @JsonProperty(access = Access.READ_ONLY)
    private int id;

    private String firstname;
    
    private String lastname;

    @Embedded
    private Address address;

    private int rating;

    @CreationTimestamp
    @JsonProperty(access = Access.READ_ONLY)
    private Date createdTimeStamp;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty(access = Access.READ_ONLY)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @JsonProperty(access = Access.READ_ONLY)
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Company company;
}
