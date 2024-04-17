package com.example.blooddonationsystem.Controller;

import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Api.ApiResponse;
import com.example.blooddonationsystem.Model.BloodBank;
import com.example.blooddonationsystem.Service.BloodBankService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bloodbank")
@RequiredArgsConstructor
public class BloodBankController {

    private final BloodBankService bloodBankService;

    @GetMapping("/get-all")
    public ResponseEntity getAllBloodBanks() {
        return ResponseEntity.status(200).body(bloodBankService.getAllBloodBanks());
    }

    @PostMapping("/add")
    public ResponseEntity addBloodBank(@RequestBody @Valid BloodBank bloodBank) {
        bloodBankService.addBloodBank(bloodBank);
        return ResponseEntity.status(200).body("Blood bank added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBloodBank(@PathVariable Integer id, @RequestBody @Valid BloodBank bloodBank) {
        bloodBankService.updateBloodBank(id, bloodBank);
        return ResponseEntity.status(200).body("Blood bank updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBloodBank(@PathVariable Integer id) {
        bloodBankService.deleteBloodBank(id);
        return ResponseEntity.status(200).body("Blood bank deleted successfully");
    }


    //    endpoint


    @GetMapping("/getAll/{isAttended}")
    public ResponseEntity getAllAppointments(@PathVariable String isAttended) {
        return ResponseEntity.status(200).body(bloodBankService.getAllAppointments(isAttended));
    }

    // blood bank can change user appointment status to attended
    @PutMapping("/attend-appointment/{bloodbank_id}/{appointment_id}")
    public ResponseEntity attendAppointment(@PathVariable Integer bloodbank_id,@PathVariable Integer appointment_id) {
        bloodBankService.attendAppointment(bloodbank_id,appointment_id);
        return ResponseEntity.status(200).body("Changed appointment status to attended successfully");
    }

    @PutMapping("/accept-appointment/{bloodBankId}/{appointmentId}/{status}")
    public ResponseEntity AcceptingOrRejectingAppointment(@PathVariable Integer bloodBankId ,@PathVariable Integer appointmentId ,@PathVariable String status){

        return ResponseEntity.status(200).body(bloodBankService.AcceptingOrRejectingAppointment(bloodBankId, appointmentId, status));
    }

    @PutMapping("/status-bank/{bankId}/{isThere}")
    public ResponseEntity IsThereABloodBank(@PathVariable Integer bankId ,@PathVariable Boolean isThere){
        return ResponseEntity.status(200).body(bloodBankService.IsThereABloodBank(bankId, isThere));
    }


}
