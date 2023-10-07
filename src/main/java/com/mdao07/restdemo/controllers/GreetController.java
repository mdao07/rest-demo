package com.mdao07.restdemo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetController {

    @Value("${greeting-word: Hello}")
    private String greeting;

    @Value("${greeting-default: ${greeting-word} Dan}")
    private String greetingName;

    @GetMapping("/{name}")
    public ResponseEntity<String> get(@PathVariable String name) {
        return new ResponseEntity<>(String.format("%s %s", greeting, name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getName() {
        return new ResponseEntity<>(greetingName, HttpStatus.OK);
    }
}
