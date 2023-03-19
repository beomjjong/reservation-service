package com.beom.reservationservice.controller;

import com.beom.reservationservice.dto.request.SaveAccountRequest;
import com.beom.reservationservice.dto.response.SaveAccountResponse;
import com.beom.reservationservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/api/account")
    public SaveAccountResponse saveAccount(@RequestBody SaveAccountRequest request) {
        return accountService.signup(request);
    }

}
