package com.example.blooddonationsystem.Controller;

import com.example.blooddonationsystem.Model.Appointment;
import com.example.blooddonationsystem.Service.AppointmentService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {


    private final AppointmentService appointmentService;



    @GetMapping("/get")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping("/add")
    public void addAppointment(@RequestBody @Valid Appointment appointment, Integer user_id,Integer bloodbank_id) {
        appointmentService.addAppointment(appointment,user_id,bloodbank_id);
    }

    @PutMapping("/update/{id}/{user_id}")
    public void updateAppointment(@PathVariable Integer id,Integer user_id, @RequestBody Appointment appointment) {
        appointmentService.updateAppointment(id, appointment,user_id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointment(id);
    }



}
