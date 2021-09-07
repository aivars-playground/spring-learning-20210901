package com.example.aspect.model;

public class MixinForPersonImpl implements MixinForPerson {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
