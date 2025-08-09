package com.entreprise.catalogue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartmentDTO {
    
    private Long id;
    
    @NotBlank(message = "Le nom du département est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String name;
    
    private int teamCount;
    
    // Constructeurs
    public DepartmentDTO() {}
    
    public DepartmentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public DepartmentDTO(Long id, String name, int teamCount) {
        this.id = id;
        this.name = name;
        this.teamCount = teamCount;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getTeamCount() {
        return teamCount;
    }
    
    public void setTeamCount(int teamCount) {
        this.teamCount = teamCount;
    }
    
    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamCount=" + teamCount +
                '}';
    }
} 