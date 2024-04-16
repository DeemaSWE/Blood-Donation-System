package com.example.blooddonationsystem.Service;

import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Model.EmergencyPatient;
import com.example.blooddonationsystem.Model.Reservation;
import com.example.blooddonationsystem.Model.User;
import com.example.blooddonationsystem.Repository.EmergencyPatientRepository;
import com.example.blooddonationsystem.Repository.ReservationRepository;
import com.example.blooddonationsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    private final EmergencyPatientRepository emergencyPatientRepository;

    private final UserRepository userRepository;


    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    public void addReservation(Reservation reservation,Integer user_id,Integer emergencyPatinet_id) {
        EmergencyPatient emergencyPatient = emergencyPatientRepository.findEmergencyPatientById(emergencyPatinet_id);
        User user=userRepository.findUserById(user_id);

        if(emergencyPatient==null){
            throw new ApiException("Emergency Patient not found");
        }

        if(user==null){
            throw new ApiException("User not found");
        }

//        String bloodTypeNeeded = reservation.getBloodType();
//        String bloodTypeAvailable = emergencyPatient.getBloodType();
//
//        if (!bloodTypeNeeded.equals(bloodTypeAvailable)) {
//            throw new ApiException("Blood type mismatch");
//        }
//        reservation.setUser(user);
        reservationRepository.save(reservation);
    }



    public void updateReservation(Integer id, Reservation reservation) {
        Reservation r = reservationRepository.findReservationById(id);

        if (r == null)
            throw new ApiException("not found");
//
//        r.setUser(reservation.getUser());
//        r.setBookedCase(reservation.getBookedCase());

        reservationRepository.save(r);
    }


    public void deleteReservation(Integer id) {
        Reservation r = reservationRepository.findReservationById(id);

        if (r == null) {
            throw new ApiException("not found");
        }
        reservationRepository.delete(r);
    }



}
