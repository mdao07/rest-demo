package com.mdao07.restdemo.controllers;

import com.mdao07.restdemo.model.Coffee;
import com.mdao07.restdemo.services.CoffeeService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {     // REST API controller

    private final CoffeeService coffeeService;

    CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> head() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.TRACE)
    public ResponseEntity<Coffee> trace(@RequestBody Coffee coffee) {
        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<?> getOptions() {
        return ResponseEntity.ok()
                .allow(HttpMethod.HEAD,
                        HttpMethod.GET,
                        HttpMethod.POST,
                        HttpMethod.PUT,
                        HttpMethod.DELETE,
                        HttpMethod.TRACE,
                        HttpMethod.OPTIONS)
                .build();
    }

    //@RequestMapping(value = "/coffees", method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<Iterable<Coffee>> getAll() {
        return new ResponseEntity<>(coffeeService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Coffee>> getById(@PathVariable String id) {
        Optional<Coffee> result = coffeeService.getById(id);

        return new ResponseEntity<>(result, result.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Coffee> create(@RequestBody Coffee coffee) {
        return new ResponseEntity<>(coffeeService.create(coffee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> update(@PathVariable String id, @RequestBody Coffee coffee) {
        return coffeeService.update(id, coffee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        coffeeService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Mapping variants
    /*
        @GetMapping()
        @PostMapping()
        @PutMapping()
        @PatchMapping()
        @DeleteMapping()
     */
}
