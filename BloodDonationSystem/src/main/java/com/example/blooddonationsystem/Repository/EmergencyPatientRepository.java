package com.example.blooddonationsystem.Repository;

import com.example.blooddonationsystem.Model.EmergencyPatient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergencyPatientRepository extends JpaRepository<EmergencyPatient , Integer> {

    EmergencyPatient findEmergencyPatientById(Integer id);
}
