package com.example.blooddonationsystem.Controller;

import com.example.blooddonationsystem.Model.Reservation;
import com.example.blooddonationsystem.Service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {



    private final ReservationService reservationService;

    @GetMapping("/get")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservation();
    }

    @PostMapping("/add/{user_id}/{emergency-patient_id}")
    public void addReservation(@RequestBody @Valid Reservation reservation, @PathVariable Integer user_id, Integer emergencyPatinet_id) {
        reservationService.addReservation(reservation,user_id,emergencyPatinet_id);
    }

    @PutMapping("/{id}")
    public void updateReservation(@PathVariable Integer id, @RequestBody Reservation reservation) {
        reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id);
    }
}
