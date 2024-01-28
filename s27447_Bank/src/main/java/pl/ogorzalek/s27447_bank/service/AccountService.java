package pl.ogorzalek.s27447_bank.service;

import pl.ogorzalek.s27447_bank.exception.AccountNotFoundException;
import pl.ogorzalek.s27447_bank.model.Account;
import pl.ogorzalek.s27447_bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ogorzalek.s27447_bank.exception.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        // Walidacja danych wejściowych
        if (account.getPesel() == null || account.getOwnerFirstName() == null || account.getOwnerLastName() == null || account.getCurrency() == null) {
            throw new InvalidAccountDataException("Niekompletne dane konta");
        }
        if (account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeBalanceException("Saldo początkowe musi być dodatnie");
        }
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        // Sprawdzenie, czy konto o podanym ID istnieje
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new AccountNotFoundException("Konto o podanym ID nie istnieje");
        }
        return account.get();
    }

    public List<Account> getAccountsWithBalanceGreaterThan(BigDecimal balance) {
        // Sprawdzenie, czy podana wartość salda jest dodatnia
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeBalanceException("Wartość salda musi być dodatnia");
        }
        return accountRepository.findByBalanceGreaterThan(balance);
    }
}
