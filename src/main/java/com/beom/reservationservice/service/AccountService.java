package com.beom.reservationservice.service;


import com.beom.reservationservice.dto.request.SaveAccountRequest;
import com.beom.reservationservice.dto.response.SaveAccountResponse;

public interface AccountService {

    SaveAccountResponse signup(SaveAccountRequest saveAccountRequest);


}
