package com.beom.reservationservice.service;

import com.beom.reservationservice.domain.Account;
import com.beom.reservationservice.domain.Role;
import com.beom.reservationservice.dto.request.SaveAccountRequest;
import com.beom.reservationservice.dto.response.SaveAccountResponse;
import com.beom.reservationservice.reposiroty.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public SaveAccountResponse signup(SaveAccountRequest saveAccountRequest) {

        //method -> 이미 가입이 된 회원인지 검증.
        // 이메일, 로그인 아이디, 전화번호가 이미 등록이 되어 있는지 검증.
        validSignupAccount(saveAccountRequest);

        Account account = Account.builder()
                .loginId(saveAccountRequest.getLoginId())
                .password(passwordEncoder.encode(saveAccountRequest.getPassword()))// 암호화 필요.
                .name(saveAccountRequest.getName())
                .phone(saveAccountRequest.getPhone())
                .email(saveAccountRequest.getEmail())
                .address(saveAccountRequest.getAddress())
                .role(Role.ROLE_MEMBER)
                .build();

        Account savedAccount = accountRepository.save(account);

        return new SaveAccountResponse(savedAccount);
    }



    private void validSignupAccount(SaveAccountRequest saveAccountRequest) {
        boolean isValidated = accountRepository.existsByEmailAndLoginIdAndPhone(saveAccountRequest.getEmail(),
                saveAccountRequest.getLoginId(),
                saveAccountRequest.getPhone());
        if (isValidated) {
            throw new IllegalArgumentException("에러 발생");
        }
    }
}
