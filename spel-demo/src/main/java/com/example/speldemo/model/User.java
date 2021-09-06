package com.example.speldemo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.TimeZone;

@Component("user")
public class User {

    @Value("#{'John Doe'}") //default value, not picked in SpelParser2EvaluationContext
    private String name;
    private String country;
    private LocalDate dob;
    private TimeZone defaultTimezone;
    private String os;

    @Value("#{user.name} is using #{user.os}")
    private String concatenatedValue;

    public User() {
    }

    @Autowired
    public User(
            @Value("#{systemProperties['user.country']}") String country,
            @Value("#{systemProperties['os.name']}") String os
    ) {
        this.country = country;
        this.os = os;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public TimeZone getDefaultTimezone() {
        return defaultTimezone;
    }

    public void setDefaultTimezone(TimeZone defaultTimezone) {
        this.defaultTimezone = defaultTimezone;
    }

    public String getConcatenatedValue() {
        return concatenatedValue;
    }

    public void setConcatenatedValue(String concatenatedValue) {
        this.concatenatedValue = concatenatedValue;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", dob=" + dob +
                ", defaultTimezone=" + defaultTimezone +
                ", os='" + os + '\'' +
                '}';
    }
}
