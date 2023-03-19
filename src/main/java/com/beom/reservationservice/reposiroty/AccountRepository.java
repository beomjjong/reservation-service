package com.beom.reservationservice.reposiroty;

import com.beom.reservationservice.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    boolean existsByEmailAndLoginIdAndPhone(String email, String loginId, String phone);
}
