package com.astroitsolutions.clienttracker.Entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Embeddable
public class Address {

    @Id
    private int id;

    private String Street;

    private String aptOrSte;

    private String city;

    private String state;

    private String zip;
    
    private String Country;
}
