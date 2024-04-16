package com.example.blooddonationsystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class EmergencyPatient {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;


    @NotEmpty(message = "patient name should be not empty")
    @Size(min = 3 , message = "patient name should be more than 3 char")
    @Column(columnDefinition = "varchar(20) not null")
    private String paitentName ;


    @NotNull(message = "paitent Numbaer should be not empty")
    @Positive(message = "positive number only")
    @Column(columnDefinition = "int not null unique")
    private Integer paitentNumbaer;


    @NotEmpty(message = "paitent Case should be not empty")
    @Size(min = 10 , message = "paitent Case should be more than 10 char")
    @Column(columnDefinition = "varchar(250) not null")
    private String paitentCase ;


    @NotEmpty(message = "blood type should be not empty")
    @Column(columnDefinition = "varchar(4) not null")
    private String bloodType ;


    @NotNull(message = "blood Donation should be not empty")
    @Positive(message = "positive number only")
    @Column(columnDefinition = "int not null")
    private Integer bloodDonation;


    @FutureOrPresent
    @Column(columnDefinition = "date")
    private LocalDate date ;

    @ManyToOne
    @JsonIgnore
    private Hospital hospital;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emergencyPatient")
    private Set<Reservation> reservations;

}
