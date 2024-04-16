package com.example.blooddonationsystem.Repository;

import com.example.blooddonationsystem.Model.BloodBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloodBankRepository extends JpaRepository<BloodBank, Integer> {

    BloodBank findBloodBankById(Integer id);
}
