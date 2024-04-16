package com.example.blooddonationsystem.Repository;

import com.example.blooddonationsystem.Model.Appointment;
import com.example.blooddonationsystem.Model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    Appointment findAppointmentById(Integer id);


}
