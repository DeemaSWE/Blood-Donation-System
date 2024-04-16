package com.example.blooddonationsystem.Service;


import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Model.HospitalVolunteer;
import com.example.blooddonationsystem.Repository.HospitalVolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalVolunteerService {


    private final HospitalVolunteerRepository hospitalVolunteerRepository ;



    public List<HospitalVolunteer> getAllHospitalVolunteer() {
        return hospitalVolunteerRepository.findAll();
    }

    public void addHospitalVolunteer(HospitalVolunteer volunteering) {
        hospitalVolunteerRepository.save(volunteering);
    }

    public void updateHospitalVolunteer(Integer id, HospitalVolunteer volunteering) {
        HospitalVolunteer volunteering1 = hospitalVolunteerRepository.findHospitalVolunteerById(id);

        if (volunteering1 == null) {
            throw new ApiException("Hospital Volunteer not found");
        }

        volunteering1.setTitle(volunteering.getTitle());
        volunteering1.setVolunteerTasks(volunteering.getVolunteerTasks());
        volunteering1.setNumberOfVolunteers(volunteering.getNumberOfVolunteers());
        volunteering1.setStartDate(volunteering.getStartDate());
        volunteering1.setStatus(volunteering.getStatus());


        hospitalVolunteerRepository.save(volunteering1);

    }

    public void deleteHospitalVolunteer(Integer id) {
        HospitalVolunteer volunteering1 = hospitalVolunteerRepository.findHospitalVolunteerById(id);

        if (volunteering1 == null) {
            throw new ApiException("Hospital Volunteer not found");
        }

        hospitalVolunteerRepository.delete(volunteering1);
    }


}
