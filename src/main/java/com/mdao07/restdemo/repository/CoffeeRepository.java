package com.mdao07.restdemo.repository;

import com.mdao07.restdemo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {

}
