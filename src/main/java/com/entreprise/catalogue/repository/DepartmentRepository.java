package com.entreprise.catalogue.repository;

import com.entreprise.catalogue.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    
    /**
     * Trouve un département par son nom
     */
    Optional<Department> findByName(String name);
    
    /**
     * Vérifie si un département existe par son nom
     */
    boolean existsByName(String name);
} 