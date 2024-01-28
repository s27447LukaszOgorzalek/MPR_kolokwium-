package pl.ogorzalek.s27447_bank.service;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import pl.ogorzalek.s27447_bank.model.Account;
import pl.ogorzalek.s27447_bank.model.Currency;
import pl.ogorzalek.s27447_bank.repository.AccountRepository;
import pl.ogorzalek.s27447_bank.exception.NegativeBalanceException;
import pl.ogorzalek.s27447_bank.exception.InvalidAccountDataException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount_WithNegativeBalance_ThrowsException() {
        Account account = new Account(null, "12345678901", new BigDecimal("-100.00"), Currency.PLN, "Jan", "Kowalski");

        assertThrows(NegativeBalanceException.class, () -> accountService.createAccount(account));
    }
}
