package org.example.services;

import org.example.entities.Bank;
import org.example.repositories.BankRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BankService {
    private final BankRepo bankRepo;

    public BankService(BankRepo bankRepo) {
        this.bankRepo = bankRepo;
    }

    @Transactional
    public void updateBanksName() {
        List<Bank> banks = bankRepo.findAll();
        updateBankName(banks);
        bankRepo.saveAllAndFlush(banks);
    }


    private void updateBankName(List<Bank> banks) {
        for (int i = 0; i < banks.size(); i++) {
            Bank bank = banks.get(i);
            bank.setName("Bank" + (i + 1));
        }
    }
}
