package com.entreprise.catalogue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TeamDTO {
    
    private Long id;
    
    @NotBlank(message = "Le nom de l'équipe est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String teamName;
    
    @Size(max = 500, message = "La description ne peut pas dépasser 500 caractères")
    private String description;
    
    @NotNull(message = "L'ID du département est obligatoire")
    private Long departmentId;
    
    private String departmentName;
    
    // Constructeurs
    public TeamDTO() {}
    
    public TeamDTO(Long id, String teamName, String description, Long departmentId) {
        this.id = id;
        this.teamName = teamName;
        this.description = description;
        this.departmentId = departmentId;
    }
    
    public TeamDTO(Long id, String teamName, String description, Long departmentId, String departmentName) {
        this.id = id;
        this.teamName = teamName;
        this.description = description;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTeamName() {
        return teamName;
    }
    
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getDepartmentId() {
        return departmentId;
    }
    
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    @Override
    public String toString() {
        return "TeamDTO{" +
                "id=" + id +
                ", teamName='" + teamName + '\'' +
                ", description='" + description + '\'' +
                ", departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
} 