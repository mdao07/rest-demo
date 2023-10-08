package com.mdao07.restdemo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/* This enforces a better type checking and
 *  a better practice
 */
@ConfigurationProperties(prefix = "greeting")
public class Greeting {

    private String word;
    private String name;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}