package com.example.swagger.model;

import io.swagger.annotations.ApiModelProperty;

public class Greeting {

    private final long id;

    @ApiModelProperty("random text for swagger")
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
