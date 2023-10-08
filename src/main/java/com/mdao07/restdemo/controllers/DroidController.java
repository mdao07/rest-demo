package com.mdao07.restdemo.controllers;

import com.mdao07.restdemo.model.Droid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/droid")
public class DroidController {

    private final Droid droid;

    DroidController(Droid droid) {
        this.droid = droid;
    }

    @GetMapping
    Droid get() {
        return droid;
    }
}
