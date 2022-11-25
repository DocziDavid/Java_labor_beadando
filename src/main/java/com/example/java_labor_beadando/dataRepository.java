package com.example.java_labor_beadando;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface dataRepository extends CrudRepository<Meccsek, Integer> {

}

