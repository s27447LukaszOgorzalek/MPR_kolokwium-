package pl.ogorzalek.s27447_bank.repository;

import pl.ogorzalek.s27447_bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * AccountRepository rozszerza JpaRepository, automatycznie dostarczając metody CRUD.
 * Spring Data JPA generuje implementację na podstawie konwencji nazewnictwa metod.
 */

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByBalanceGreaterThan(BigDecimal balance);
}

