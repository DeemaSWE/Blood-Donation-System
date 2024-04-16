package com.example.blooddonationsystem.Service;

import com.example.blooddonationsystem.Api.ApiException;
import com.example.blooddonationsystem.Model.BloodBank;
import com.example.blooddonationsystem.Repository.BloodBankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BloodBankService {

    private final BloodBankRepository bloodBankRepository;

    public List<BloodBank> getAllBloodBanks() {
        return bloodBankRepository.findAll();
    }

    public void addBloodBank(BloodBank bloodBank) {
        bloodBankRepository.save(bloodBank);
    }

    public void updateBloodBank(Integer id, BloodBank updatedBloodBank) {
        BloodBank bloodBank = bloodBankRepository.findBloodBankById(id);

        if (bloodBank == null)
            throw new ApiException("Blood bank not found");

        bloodBank.setName(updatedBloodBank.getName());
        bloodBank.setCity(updatedBloodBank.getCity());

        bloodBankRepository.save(bloodBank);
    }

    public void deleteBloodBank(Integer id) {
        BloodBank bloodBank = bloodBankRepository.findBloodBankById(id);

        if (bloodBank == null)
            throw new ApiException("Blood bank not found");

        bloodBankRepository.delete(bloodBank);
    }
}
