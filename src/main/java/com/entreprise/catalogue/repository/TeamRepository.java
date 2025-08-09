package com.entreprise.catalogue.repository;

import com.entreprise.catalogue.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    
    /**
     * Trouve toutes les équipes d'un département
     */
    List<Team> findByDepartmentId(Long departmentId);
    
    /**
     * Trouve toutes les équipes d'un département avec le nom du département
     */
    @Query("SELECT t FROM Team t JOIN FETCH t.department WHERE t.department.id = :departmentId")
    List<Team> findByDepartmentIdWithDepartment(@Param("departmentId") Long departmentId);
    
    /**
     * Trouve une équipe par son nom
     */
    List<Team> findByTeamNameContainingIgnoreCase(String teamName);
    
    /**
     * Vérifie si une équipe existe par son nom dans un département
     */
    boolean existsByTeamNameAndDepartmentId(String teamName, Long departmentId);
} 