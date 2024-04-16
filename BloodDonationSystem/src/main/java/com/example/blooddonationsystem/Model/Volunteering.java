package com.example.blooddonationsystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Volunteering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;


    @NotEmpty(message = "name should be not empty")
    @Size(min = 3 , message = "name should be more than 3 char")
    @Column(columnDefinition = "varchar(20) not null")
    private String name ;


    @NotNull(message = "age should be not empty")
    @Positive(message = "positive number only")
    @Min(value = 18)
    @Column(columnDefinition = "int not null")
    private Integer age ;


    @NotNull(message = "age should be not empty")
    @Positive(message = "positive number only")
    @Column(columnDefinition = "int not null")
    private String phoneNumber ;


    @NotEmpty(message = "please enter your major")
    @Column(columnDefinition = "varchar(25) not null")
    private String major ;


    @Email
    @NotEmpty(message = "please enter your email")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email ;


    @ManyToMany
    @JsonIgnore
    private Set<HospitalVolunteer> hospitalVolunteers;

    @ManyToMany
    @JsonIgnore
    private Set<Reservation> reservations;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "volunteering")
    private Set<Appointment> appointments;

}
