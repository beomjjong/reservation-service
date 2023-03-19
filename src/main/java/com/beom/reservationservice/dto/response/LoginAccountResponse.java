package com.beom.reservationservice.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginAccountResponse {
    private String loginId;

    private String name;

    private String email;

    private String token;
}
