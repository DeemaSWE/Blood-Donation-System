package com.example.blooddonationsystem.Service;


import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Model.EmergencyPatient;
import com.example.blooddonationsystem.Repository.EmergencyPatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyPatientService {

    private final EmergencyPatientRepository emergencyPatientRepository ;


    public List<EmergencyPatient> getAllEmergencyPatient() {
        return emergencyPatientRepository.findAll();
    }

    public void addEmergencyPatient(EmergencyPatient emergencyPatient) {
        emergencyPatientRepository.save(emergencyPatient);
    }

    public void updateEmergencyPatient(Integer id, EmergencyPatient emergencyPatient) {
        EmergencyPatient emergencyPatient1 = emergencyPatientRepository.findEmergencyPatientById(id);

        if (emergencyPatient1 == null) {
            throw new ApiException("Emergency Patient not found");
        }

        emergencyPatient1.setBloodDonation(emergencyPatient.getBloodDonation());
        emergencyPatient1.setBloodType(emergencyPatient.getBloodType());
        emergencyPatient1.setPaitentCase(emergencyPatient.getPaitentCase());
        emergencyPatient1.setPaitentName(emergencyPatient.getPaitentName());
        emergencyPatient1.setPaitentNumbaer(emergencyPatient.getPaitentNumbaer());


        emergencyPatientRepository.save(emergencyPatient1);
    }


    public void deleteEmergencyPatient(Integer id) {
        EmergencyPatient emergencyPatient1 = emergencyPatientRepository.findEmergencyPatientById(id);

        if (emergencyPatient1 == null) {
            throw new ApiException("Emergency Patient not found");
        }
        emergencyPatientRepository.delete(emergencyPatient1);
    }

}
