package com.bank.bankService.controller;


import com.bank.bankService.model.Bank;
import com.bank.bankService.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/banks")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/{zip}")
    public List<Bank> getBanks(@PathVariable String zip) {
        return bankService.getBanksNearZip(zip);
    }
}

