package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entity.ServiceBasic;
import java.util.Optional;

public interface ServiceBasicRepository extends JpaRepository<ServiceBasic, Long> {         
    Optional<ServiceBasic> findByName(String name);
}
