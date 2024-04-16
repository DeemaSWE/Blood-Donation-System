package com.example.blooddonationsystem.Model;

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
@NoArgsConstructor
@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot ne empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "City cannot be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private Set<EmergencyPatient> emergencyPatients;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital")
    private Set<HospitalVolunteer> hospitalVolunteers;
}
