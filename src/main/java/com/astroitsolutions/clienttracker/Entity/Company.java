package com.astroitsolutions.clienttracker.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@ToString
public class Company {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private Address headquartersAddress;

    @Enumerated(value = EnumType.STRING)
    private CompanySize companySize;

    private boolean isInternational;

    // Employees of this company are clients of the business.
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonProperty(access = Access.READ_ONLY)
    @JsonIgnore
    @ToString.Exclude
    private List<Client> employeesWhoAreClients;

    private double discountPercentage;
}
