package com.example.java_labor_beadando.kapcsolat;

import com.example.java_labor_beadando.securityrole.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message,Integer> {
    Optional<Message> findByName(String name);
    List<Message> findByOrderByDateDesc();
}
