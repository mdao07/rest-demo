package com.mdao07.restdemo.controllers;

import com.mdao07.restdemo.model.Coffee;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {     // REST API controller

    private List<Coffee> coffees;

    CoffeeController() {
        coffees = new ArrayList<>();
        coffees.add(new Coffee("Café Cereza"));
        coffees.add(new Coffee("Café Ganador"));
        coffees.add(new Coffee("Café Lareño"));
        coffees.add(new Coffee("Café Trés Pontas"));
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
        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Coffee>> getById(@PathVariable String id) {
        Optional<Coffee> result = coffees.stream().filter(coffee -> coffee.getId().equals(id)).findFirst();

        return new ResponseEntity<>(result, result.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Coffee> create(@RequestBody Coffee coffee) {
        return new ResponseEntity<>(createCoffee(coffee), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> update(@PathVariable String id, @RequestBody Coffee coffee) {
        Optional<Coffee> result = coffees.stream()
                                        .filter(c -> c.getId().equals(id))
                                        .findFirst();
        Coffee target;
        HttpStatus code;

        if (result.isPresent()) {
            target = result.get();
            target.setName(coffee.getName());
            code = HttpStatus.OK;
        } else {
            target = createCoffee(id, coffee);
            code = HttpStatus.CREATED;
        }

        return new ResponseEntity<>(target, code);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        coffees.removeIf(coffee -> coffee.getId().equals(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    Coffee createCoffee(Coffee coffee) {
        Coffee newCoffee = new Coffee(coffee.getName());
        coffees.add(newCoffee);

        return newCoffee;
    }

    Coffee createCoffee(String id, Coffee coffee) {
        Coffee newCoffee = new Coffee(id, coffee.getName());
        coffees.add(newCoffee);

        return newCoffee;
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
