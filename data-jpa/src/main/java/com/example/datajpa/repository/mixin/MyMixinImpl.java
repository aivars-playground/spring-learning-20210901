package com.example.datajpa.repository.mixin;

public class MyMixinImpl implements MyMixin {

    @Override
    public String getMixinMethod() {
        return "Hi From Mixin";
    }
}
