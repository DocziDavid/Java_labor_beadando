package com.example.java_labor_beadando;

import com.example.java_labor_beadando.modelclasses.Belepesek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface dataIIIRepository extends CrudRepository<Belepesek, Integer> {
}
