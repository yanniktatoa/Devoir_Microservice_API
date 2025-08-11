package com.entreprise.catalogue.department.controller;

import com.entreprise.catalogue.department.dto.DepartmentDTO;
import com.entreprise.catalogue.department.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;
    
    // GET /api/departments - Liste tous les départements
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }
    
    // GET /api/departments/{id} - Récupère un département par ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentDTO> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // GET /api/departments/search?name={name} - Recherche par nom
    @GetMapping("/search")
    public ResponseEntity<List<DepartmentDTO>> searchDepartmentsByName(@RequestParam String name) {
        List<DepartmentDTO> departments = departmentService.searchDepartmentsByName(name);
        return ResponseEntity.ok(departments);
    }
    
    // POST /api/departments - Crée un nouveau département
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        try {
            DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // PUT /api/departments/{id} - Met à jour un département
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDTO> updateDepartment(@PathVariable Long id, 
                                                       @Valid @RequestBody DepartmentDTO departmentDTO) {
        try {
            Optional<DepartmentDTO> updatedDepartment = departmentService.updateDepartment(id, departmentDTO);
            return updatedDepartment.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // DELETE /api/departments/{id} - Supprime un département
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        boolean deleted = departmentService.deleteDepartment(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // GET /api/departments/count - Compte le nombre de départements
    @GetMapping("/count")
    public ResponseEntity<Long> countDepartments() {
        long count = departmentService.countDepartments();
        return ResponseEntity.ok(count);
    }
    
    // GET /api/departments/{id}/exists - Vérifie si un département existe
    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> departmentExists(@PathVariable Long id) {
        boolean exists = departmentService.departmentExists(id);
        return ResponseEntity.ok(exists);
    }
} 