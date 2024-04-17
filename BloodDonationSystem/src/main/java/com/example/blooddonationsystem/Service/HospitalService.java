package com.example.blooddonationsystem.Service;

import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Model.*;
import com.example.blooddonationsystem.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final EmergencyPatientRepository emergencyPatientRepository;
    private final ReservationRepository resRepository ;
    private final HospitalVolunteerRepository hospitalVolunteerRepository ;
    private final VolunteeringRepository volunteeringRepository;
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


    //    endpoint



    // get emergency patients cases that are completed for a hospital
    public List<EmergencyPatient> getCompletedEmergencyPatients(Integer hospital_id) {
        return emergencyPatientRepository.findEmergencyPatientsByHospitalIdAndStatus(hospital_id, "completed");
    }

    // تغيير حالة احد الحالات الحرجة
    public void changeBloodDonationStatus(Integer hospitalId, Integer caseId, String status){
        Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
        EmergencyPatient emergencyPatient = emergencyPatientRepository.findEmergencyPatientById(caseId);

        if (hospital == null || emergencyPatient == null){
            throw new ApiException("Hospital or emergency patient not found");
        }

        emergencyPatient.setStatus(status);

        emergencyPatientRepository.save(emergencyPatient);
    }


    //  قبول او رفض التطوع

    public String AcceptingOrRejectingVolunteering(Integer hospitalId, Integer HvolunteerId , Integer volunteerId , String status ){
        Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
        HospitalVolunteer hospitalVolunteer = hospitalVolunteerRepository.findHospitalVolunteerById(HvolunteerId);
        Volunteering volunteering = volunteeringRepository.findVolunteeringById(volunteerId);

        if (hospital == null || hospitalVolunteer == null || volunteerId == null){
            throw new ApiException("Hospital or volunteer not found");
        }

        hospitalVolunteer.setStatus(status);

        hospitalVolunteerRepository.save(hospitalVolunteer);

        return "status change to " + status;
    }


     //    قبول او رفض موعد التبرع للدم للحالة الطارئة

    public String AcceptingOrRejectingBloodDonation(Integer hospitalId, Integer reservationId , String status ){
        Hospital hospital = hospitalRepository.findHospitalById(hospitalId);
        Reservation reservation = resRepository.findReservationById(reservationId);

        if (hospital == null || reservation == null){
            throw new ApiException("Hospital or reservation not found");
        } else if (reservation.getEmergencyPatient().getHospital().getId().equals(hospitalId)) {

            reservation.setStatus(status);

            resRepository.save(reservation);

            return "status change to " + status;
        }

        return "invalid hospital id";

    }


    // hospital can rate a volunteer
    public void rateVolunteer(Integer hospital_id, Integer volunteer_id, Double rating) {
        HospitalVolunteer hospitalVolunteer = hospitalVolunteerRepository.findHospitalVolunteerById(volunteer_id);
        Volunteering volunteering = volunteeringRepository.findVolunteeringById(volunteer_id);

        if (hospitalVolunteer == null)
            throw new ApiException("Volunteer not found");

        if (!hospitalVolunteer.getHospital().getId().equals(hospital_id))
            throw new ApiException("Hospital is not authorized to rate this volunteer");

        // check if the volunteer did volunteer in the hospital
        Set<Volunteering> volunteerings = hospitalVolunteer.getVolunteerings();
        if (!volunteerings.contains(volunteering))
            throw new ApiException("Volunteer did not volunteer in this hospital");

        if(rating < 0 || rating > 5)
            throw new ApiException("Rating should be between 0 and 5");

        // calculate new average rating for volunteer
        double averageRating = volunteering.getAverageRating() == null ? 0 : volunteering.getAverageRating();
        int ratingCount = volunteering.getRatingCount() == null ? 0 : volunteering.getRatingCount();

        double newAverageRating = (averageRating * ratingCount + rating) / (ratingCount + 1);
        volunteering.setAverageRating(newAverageRating);
        volunteering.setRatingCount(ratingCount + 1);

        volunteeringRepository.save(volunteering);

    }


}
