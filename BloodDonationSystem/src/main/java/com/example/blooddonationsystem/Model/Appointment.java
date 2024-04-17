package com.example.blooddonationsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @FutureOrPresent
    @Column(columnDefinition = "date")
    private LocalDateTime time;


    @Pattern(regexp = "^(unattended|attended)$")
    //unattended, attended
    private String attended;

    @Pattern(regexp = "^(pending|accepted|rejected|cancelled)$")
    //pending, accepted, rejected, cancelled
    private String status;

    private Double rating;

    @ManyToOne
    @JsonIgnore
    private BloodBank bloodBank;

    @ManyToOne
    @JsonIgnore
    private User user;

    @ManyToOne
    @JsonIgnore
    private Volunteering volunteering;
}
