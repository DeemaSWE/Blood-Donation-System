package com.example.blooddonationsystem.Service;

import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Model.Hospital;
import com.example.blooddonationsystem.Repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public void addHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    public void updateHospital(Integer id, Hospital updatedHospital) {
        Hospital hospital = hospitalRepository.findHospitalById(id);

        if (hospital == null)
            throw new ApiException("Hospital not found");

        hospital.setName(updatedHospital.getName());
        hospital.setCity(updatedHospital.getCity());

        hospitalRepository.save(hospital);
    }

    public void deleteHospital(Integer id) {
        Hospital hospital = hospitalRepository.findHospitalById(id);

        if (hospital == null)
            throw new ApiException("Hospital not found");

        hospitalRepository.delete(hospital);
    }
}
