package com.example.securityfundamentals;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        System.out.println("-----------ServletInitializer:SpringApplicationBuilder-");
        return application.sources(SecurityFundamentalsApplication.class);
    }

}
