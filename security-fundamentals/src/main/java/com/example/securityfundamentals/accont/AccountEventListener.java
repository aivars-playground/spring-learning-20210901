package com.example.securityfundamentals.accont;

import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class AccountEventListener implements ApplicationListener<AccountOnCreateEvent> {

    private String url = "http://localhost:8080/";

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private AccountService accountService;

    @Override
    public void onApplicationEvent(AccountOnCreateEvent event) {
        this.sendAccountCreationConfirmationEmail(event);
    }

    private void sendAccountCreationConfirmationEmail(AccountOnCreateEvent event) {
        System.out.println("---------AccountEventListener:sending...");

        //get account
        Account account = event.getAccount();

        //create verification token
        var verificationToken = accountService.createVerificationToken(account);

        //prepare email
        String recipient = account.getEmail();
        String subject = "Account Confirmation Request";
        String url = event.getAppUrl() + "/create_account_confirm?token=" + verificationToken.getToken();

        SimpleMailMessage email = new SimpleMailMessage();
        email.setCc(recipient);
        email.setSubject(subject);
        email.setText(url);
        email.setFrom("user@localhost");

        javaMailSender.send(email);

    }
}
