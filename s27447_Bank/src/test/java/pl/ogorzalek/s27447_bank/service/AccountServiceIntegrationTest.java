package pl.ogorzalek.s27447_bank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.ogorzalek.s27447_bank.model.Account;
import pl.ogorzalek.s27447_bank.model.Currency;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AccountServiceIntegrationTest {

    @Autowired
    private AccountService accountService;

    /* @BeforeEach
    public void setUp() {
    } */

    @Test
    public void testCreateAccount() {
        Account account = new Account(null, "12345678901", new BigDecimal("100.00"), Currency.PLN, "Jan", "Kowalski");

        Account createdAccount = accountService.createAccount(account);

        assertNotNull(createdAccount.getId());
        assertEquals("12345678901", createdAccount.getPesel());
        assertEquals(new BigDecimal("100.00"), createdAccount.getBalance());
        assertEquals(Currency.PLN, createdAccount.getCurrency());
        assertEquals("Jan", createdAccount.getOwnerFirstName());
        assertEquals("Kowalski", createdAccount.getOwnerLastName());
    }
}
