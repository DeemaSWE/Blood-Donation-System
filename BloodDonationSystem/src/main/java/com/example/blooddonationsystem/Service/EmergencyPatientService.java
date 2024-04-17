package com.example.blooddonationsystem.Service;


import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Model.EmergencyPatient;
import com.example.blooddonationsystem.Model.Hospital;
import com.example.blooddonationsystem.Repository.EmergencyPatientRepository;
import com.example.blooddonationsystem.Repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergencyPatientService {

    private final EmergencyPatientRepository emergencyPatientRepository ;
    private final HospitalRepository hospitalRepository ;


    public List<EmergencyPatient> getAllEmergencyPatient() {
        return emergencyPatientRepository.findAll();
    }

    public void addEmergencyPatient(EmergencyPatient emergencyPatient , Integer HospitalId) {

        Hospital hospital = hospitalRepository.findHospitalById(HospitalId);
        if (hospital == null){
            throw new ApiException("hospital not found");
        }

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

//    endpoint

    public List<EmergencyPatient> sortCases(){
        List<EmergencyPatient> veryUrgent = emergencyPatientRepository.getEmergencyPatientsByEmergencyStatus("very urgent");
        List<EmergencyPatient> urgent = emergencyPatientRepository.getEmergencyPatientsByEmergencyStatus("urgent");

        veryUrgent.addAll(urgent);
        return veryUrgent;
    }


}
