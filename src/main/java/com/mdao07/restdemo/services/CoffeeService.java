package com.mdao07.restdemo.services;

import com.mdao07.restdemo.model.Coffee;
import com.mdao07.restdemo.repository.CoffeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @PostConstruct
    private void loadData() {
        List<Coffee> list = List.of(
                new Coffee("Café Cereza"),
                new Coffee("Café Ganador"),
                new Coffee("Café Lareño"),
                new Coffee("Café Trés Pontas"));

        coffeeRepository.saveAll(list);
    }

    public Iterable<Coffee> getAll() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> getById(String id) {
        return coffeeRepository.findById(id);
    }

    public Coffee create(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public ResponseEntity<Coffee> update(String id, Coffee coffee) {
        boolean exists = coffeeRepository.existsById(id);
        HttpStatus code = exists ? HttpStatus.OK : HttpStatus.CREATED;

        coffee.setId(id);
        coffeeRepository.save(coffee);

        return new ResponseEntity<>(coffee, code);
    }

    public void delete(String id) {
        coffeeRepository.deleteById(id);
    }
}
