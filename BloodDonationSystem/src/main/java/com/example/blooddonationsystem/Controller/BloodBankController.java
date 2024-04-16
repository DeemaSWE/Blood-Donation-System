package com.example.blooddonationsystem.Controller;

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

}
