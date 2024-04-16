package com.example.blooddonationsystem.Service;


import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Model.Volunteering;
import com.example.blooddonationsystem.Repository.VolunteeringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteeringService {


    private final VolunteeringRepository volunteeringRepository ;



    public List<Volunteering> getAllVolunteering() {
        return volunteeringRepository.findAll();
    }

    public void addVolunteering(Volunteering volunteering) {
        volunteeringRepository.save(volunteering);
    }

    public void updateVolunteering(Integer id, Volunteering volunteering) {
        Volunteering volunteering1 = volunteeringRepository.findVolunteeringById(id);

        if (volunteering1 == null) {
            throw new ApiException("Volunteering not found");
        }

        volunteering1.setName(volunteering.getName());
        volunteering1.setAge(volunteering.getAge());
        volunteering1.setEmail(volunteering.getEmail());
        volunteering1.setMajor(volunteering.getMajor());
        volunteering1.setPhoneNumber(volunteering.getPhoneNumber());


        volunteeringRepository.save(volunteering1);
    }


    public void deleteVolunteering(Integer id) {
        Volunteering volunteering1 = volunteeringRepository.findVolunteeringById(id);

        if (volunteering1 == null) {
            throw new ApiException("Volunteering not found");
        }
        volunteeringRepository.delete(volunteering1);
    }
}
