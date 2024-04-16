package com.example.blooddonationsystem.Controller;


import com.example.blooddonationsystem.Model.Volunteering;
import com.example.blooddonationsystem.Service.VolunteeringService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/volunteer")
@RequiredArgsConstructor
public class VolunteeringController {


    private final VolunteeringService volunteeringService ;


    @GetMapping("/get-all")
    public ResponseEntity getAllVolunteering() {
        return ResponseEntity.status(200).body(volunteeringService.getAllVolunteering());
    }

    @PostMapping("/add")
    public ResponseEntity addVolunteering(@RequestBody @Valid Volunteering volunteering) {
        volunteeringService.addVolunteering(volunteering);
        return ResponseEntity.status(200).body("Volunteering added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateVolunteering(@PathVariable Integer id, @RequestBody @Valid Volunteering volunteering) {
        volunteeringService.updateVolunteering(id, volunteering);
        return ResponseEntity.status(200).body("Volunteering updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteVolunteering(@PathVariable Integer id) {
        volunteeringService.deleteVolunteering(id);
        return ResponseEntity.status(200).body("Volunteering deleted successfully");
    }

}
