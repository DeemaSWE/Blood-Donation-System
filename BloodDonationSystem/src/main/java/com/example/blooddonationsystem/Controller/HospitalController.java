package com.example.blooddonationsystem.Controller;

import com.example.blooddonationsystem.Model.Hospital;
import com.example.blooddonationsystem.Service.HospitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hospital")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/get-all")
    public ResponseEntity getAllHospitals() {
        return ResponseEntity.status(200).body(hospitalService.getAllHospitals());
    }

    @PostMapping("/add")
    public ResponseEntity addHospital(@RequestBody @Valid Hospital hospital) {
        hospitalService.addHospital(hospital);
        return ResponseEntity.status(200).body("Hospital added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateHospital(@PathVariable Integer id, @RequestBody @Valid Hospital hospital) {
        hospitalService.updateHospital(id, hospital);
        return ResponseEntity.status(200).body("Hospital updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteHospital(@PathVariable Integer id) {
        hospitalService.deleteHospital(id);
        return ResponseEntity.status(200).body("Hospital deleted successfully");
    }

}
