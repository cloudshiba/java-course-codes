package org.example.controller;

import org.example.AccountInfo;
import org.example.service.Bank1AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank1")
public class AccountController {
    @Autowired
    private Bank1AccountService bank1AccountService;


    @RequestMapping("/transfer")
    public Boolean transferNest() {
        return bank1AccountService.decreaseBalance("zs", 1d);
    }

    @GetMapping("/get")
    public AccountInfo get(@RequestParam("name") String name){
        return bank1AccountService.selectByName(name);
    }


}
