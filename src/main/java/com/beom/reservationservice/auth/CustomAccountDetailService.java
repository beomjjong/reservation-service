package com.beom.reservationservice.auth;

import com.beom.reservationservice.domain.Account;
import com.beom.reservationservice.reposiroty.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CustomAccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      Account account = accountRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);

        return new CustomAccountDetails(account);
    }
}
