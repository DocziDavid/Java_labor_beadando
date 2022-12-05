package com.example.java_labor_beadando;

import com.example.java_labor_beadando.modelclasses.Nezok;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface dataIIRepository extends CrudRepository<Nezok, Integer> {
}
