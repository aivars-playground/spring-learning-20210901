package com.example.securityfundamentals.accont;

import org.springframework.context.ApplicationEvent;

public class AccountOnCreateEvent extends ApplicationEvent {

    private Account account;
    private String appUrl;

    public AccountOnCreateEvent(Account account, String appUrl) {
        super(account);
        this.account = account;
        this.appUrl = appUrl;
    }

    public Account getAccount() {
        return account;
    }

    public String getAppUrl() {
        return appUrl;
    }
}
