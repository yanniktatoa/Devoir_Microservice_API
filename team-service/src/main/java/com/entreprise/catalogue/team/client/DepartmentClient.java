package com.entreprise.catalogue.team.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department-service")
public interface DepartmentClient {
    
    @GetMapping("/api/departments/{id}")
    DepartmentDTO getDepartmentById(@PathVariable("id") Long id);
    
    @GetMapping("/api/departments/{id}/exists")
    boolean departmentExists(@PathVariable("id") Long id);
    
    // DTO interne pour la communication
    class DepartmentDTO {
        private Long id;
        private String name;
        private String createdAt;
        private String updatedAt;
        
        public DepartmentDTO() {}
        
        public DepartmentDTO(Long id, String name) {
            this.id = id;
            this.name = name;
        }
        
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
        
        public String getCreatedAt() {
            return createdAt;
        }
        
        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
        
        public String getUpdatedAt() {
            return updatedAt;
        }
        
        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
} 