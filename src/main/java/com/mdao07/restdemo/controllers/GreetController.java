package com.mdao07.restdemo.controllers;

import com.mdao07.restdemo.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetController {

    private final Greeting greeting;

    GreetController(Greeting greeting) {
        this.greeting = greeting;
    }

    @GetMapping("/{name}")
    public ResponseEntity<String> get(@PathVariable String name) {
        return new ResponseEntity<>(String.format("%s %s", greeting.getWord(), name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getName() {
        return new ResponseEntity<>(greeting.getName(), HttpStatus.OK);
    }
}
