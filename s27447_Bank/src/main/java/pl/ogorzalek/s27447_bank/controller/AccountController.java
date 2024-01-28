package pl.ogorzalek.s27447_bank.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.ogorzalek.s27447_bank.model.Account;
import pl.ogorzalek.s27447_bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);
        if (account == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(account);
    }

    @GetMapping("/balanceGreaterThan/{balance}")
    public List<Account> getAccountsWithBalanceGreaterThan(@PathVariable BigDecimal balance) {
        return accountService.getAccountsWithBalanceGreaterThan(balance);
    }
}

