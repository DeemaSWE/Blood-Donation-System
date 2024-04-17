package com.example.blooddonationsystem.Controller;


import com.example.blooddonationsystem.Model.EmergencyPatient;
import com.example.blooddonationsystem.Service.EmergencyPatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emergency-patient")
@RequiredArgsConstructor
public class EmergencyPatientController {

    private final EmergencyPatientService emergencyPatientService ;


    @GetMapping("/get-all")
    public ResponseEntity getAllEmergencyPatient() {
        return ResponseEntity.status(200).body(emergencyPatientService.getAllEmergencyPatient());
    }

    @PostMapping("/add/{hospitalId}")
    public ResponseEntity addEmergencyPatient(@RequestBody @Valid EmergencyPatient emergencyPatient ,@PathVariable Integer hospitalId ){
        emergencyPatientService.addEmergencyPatient(emergencyPatient, hospitalId);
        return ResponseEntity.status(200).body("Emergency Patient added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmergencyPatient(@PathVariable Integer id, @RequestBody @Valid EmergencyPatient emergencyPatient) {
        emergencyPatientService.updateEmergencyPatient(id, emergencyPatient);
        return ResponseEntity.status(200).body("Emergency Patient updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmergencyPatient(@PathVariable Integer id) {
        emergencyPatientService.deleteEmergencyPatient(id);
        return ResponseEntity.status(200).body("Emergency Patient deleted successfully");
    }

    //    endpoint

    // sort
    @GetMapping("/sort-cases")
    public ResponseEntity sortCases(){
        return ResponseEntity.status(200).body(emergencyPatientService.sortCases());
    }

}
