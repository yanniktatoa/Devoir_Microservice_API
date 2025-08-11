package com.entreprise.catalogue.team.repository;

import com.entreprise.catalogue.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    
    List<Team> findByDepartmentId(Long departmentId);
    
    Optional<Team> findByName(String name);
    
    boolean existsByName(String name);
    
    @Query("SELECT t FROM Team t WHERE t.name LIKE %:name%")
    List<Team> findByNameContaining(@Param("name") String name);
    
    @Query("SELECT COUNT(t) FROM Team t WHERE t.departmentId = :departmentId")
    long countByDepartmentId(@Param("departmentId") Long departmentId);
    
    @Query("SELECT COUNT(t) FROM Team t")
    long countTeams();
} 