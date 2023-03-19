package com.beom.service;

import com.beom.reservationservice.domain.Address;
import com.beom.reservationservice.dto.request.SaveAccountRequest;
import com.beom.reservationservice.dto.response.SaveAccountResponse;
import com.beom.reservationservice.ReservationServiceApplication;
import com.beom.reservationservice.reposiroty.AccountRepository;
import com.beom.reservationservice.service.AccountService;
import com.beom.reservationservice.service.AccountServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest(classes = ReservationServiceApplication.class)
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    public void init() {
        accountRepository.deleteAll();
    }

    @Test
    void signup_test_success() {
        //given
        Address address = getAddress();

        SaveAccountRequest saveAccountRequest = getSaveAccountRequest(address, "son1234", "password!", "son", "010-1234-1234", "email@email.com");
        //when
        SaveAccountResponse saveAccountResponse = accountService.signup(saveAccountRequest);

        //then
        assertThat(saveAccountResponse.getLoginId()).isEqualTo(saveAccountRequest.getLoginId());
        assertThat(saveAccountResponse.getId()).isEqualTo(accountRepository.findAll().get(0).getId());
    }

    @Test
    void duplicate_signup_test() {
        //given
        Address address = getAddress();

        SaveAccountRequest saveAccountRequestA = getSaveAccountRequest(address,
                "son1234",
                "password!",
                "son",
                "010-1234-1234",
                "email@email.com");

        SaveAccountRequest saveAccountRequestB = getSaveAccountRequest(address,
                "son1234",
                "password!",
                "son",
                "010-1234-1234",
                "email@email.com");
        //when
        accountService.signup(saveAccountRequestA);

        //then
        Assertions.assertThatThrownBy(() -> accountService.signup(saveAccountRequestB))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void passwordEncodeSuccess() {
        //given
        Address address = getAddress();

        SaveAccountRequest saveAccountRequest = getSaveAccountRequest(address,
                "son1234",
                "password12@",
                "son",
                "010-1234-1234",
                "email@email.com");
        //when
        accountService.signup(saveAccountRequest);
        //then
//        assertThat(saveAccountRequest.getPassword()).isEqualTo("password12@");
        assertThat(accountRepository.findAll().get(0).getPassword()).isNotEqualTo("password12@");
    }



    private static Address getAddress() {
        Address address = new Address();
        address.setStreet("강남대로");
        address.setZipcode("12345");
        return address;
    }

    private static SaveAccountRequest getSaveAccountRequest(Address address,
                                                            String loginId,
                                                            String password,
                                                            String name,
                                                            String phone,
                                                            String email) {
        SaveAccountRequest saveAccountRequest = new SaveAccountRequest();
        saveAccountRequest.setAddress(address);
        saveAccountRequest.setLoginId(loginId);
        saveAccountRequest.setPassword(password);
        saveAccountRequest.setName(name);
        saveAccountRequest.setPhone(phone);
        saveAccountRequest.setEmail(email);
        return saveAccountRequest;
    }


}