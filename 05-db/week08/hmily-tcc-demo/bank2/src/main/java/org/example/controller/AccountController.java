package org.example.controller;

import org.example.service.Bank2AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank2")
public class AccountController {
    @Autowired
    private Bank2AccountService bank2AccountService;
    @GetMapping("/hi")
    public String hello(){
        return "hi,this is bank2!";
    }

    @RequestMapping("/transfer")
    public Boolean test2(@RequestParam("amount") Double amount) {
        this.bank2AccountService.increaseAccountBalance("ls", amount);
        return true;
    }
}
