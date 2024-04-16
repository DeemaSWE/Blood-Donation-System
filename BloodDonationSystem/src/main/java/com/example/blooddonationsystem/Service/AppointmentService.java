package com.example.blooddonationsystem.Service;

import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Model.Appointment;
import com.example.blooddonationsystem.Model.BloodBank;
import com.example.blooddonationsystem.Model.User;
import com.example.blooddonationsystem.Repository.AppointmentRepository;
import com.example.blooddonationsystem.Repository.BloodBankRepository;
import com.example.blooddonationsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final BloodBankRepository bloodBankRepository;




    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void addAppointment(Appointment appointment, Integer user_id, Integer bloodbank_id) {
        User u = userRepository.findUserById(user_id);
        BloodBank b = bloodBankRepository.findBloodBankById(bloodbank_id);

        if (u == null || b == null) {
            throw new ApiException("User or BloodBank not found");
        }
        appointmentRepository.save(appointment);
    }

    public void updateAppointment(Integer id, Appointment appointment,Integer user_id) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ApiException("Appointment not found"));

//        if (!existingAppointment.getUser().getId().equals(user_id)) {
//            throw new ApiException("User is not authorized to update this appointment");
//        }

        existingAppointment.setTime1(appointment.getTime1());
        existingAppointment.setAttended(appointment.getAttended());

        appointmentRepository.save(existingAppointment);
    }

    public void deleteAppointment(Integer id) {
        Appointment a = appointmentRepository.findAppointmentById(id);
                if(a==null)
                    throw new ApiException("Appointment not found");

        appointmentRepository.delete(a);
    }



}
