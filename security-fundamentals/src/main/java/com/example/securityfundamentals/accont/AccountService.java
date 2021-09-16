package com.example.securityfundamentals.accont;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
public class AccountService {

    @Resource
    AccountRepository accountRepository;

    @Resource
    VerificationTokenRepository verificationTokenRepository;

    public Account create(Account account) {
        return accountRepository.save(account);
    }

    public VerificationToken createVerificationToken(final Account account) {
        //create token
        String token = UUID.randomUUID().toString();
        var verificationToken = new VerificationToken();
        verificationToken.setUsername(account.getUsername());
        verificationToken.setToken(token);
        return verificationTokenRepository.save(verificationToken);
    }

    public void confirmAccount(String token) {
        VerificationToken vt = verificationTokenRepository.findById(token).get();
        Account acc = accountRepository.findById(vt.getUsername()).get();
        System.out.println("----enabling:"+acc.getFirstName());
    }
}
