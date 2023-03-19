package com.beom.reservationservice.dto.response;

import com.beom.reservationservice.domain.Account;
import com.beom.reservationservice.domain.Address;
import com.beom.reservationservice.domain.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SaveAccountResponse {
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private String email;

    private String phone;

    private Address address;

    private Role role;

    public SaveAccountResponse(Account account) {
        this.id = account.getId();
        this.loginId = account.getLoginId();
        this.password = account.getPassword();
        this.name = account.getName();
        this.email = account.getEmail();
        this.phone = account.getPhone();
        this.address = account.getAddress();
        this.role = account.getRole();
    }
}
