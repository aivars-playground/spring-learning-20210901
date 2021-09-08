package com.example.webmvc.model;

import javax.validation.constraints.NotEmpty;

public class Registration {

    @NotEmpty
    private String name;

    @NotEmpty
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
