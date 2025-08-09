package com.entreprise.catalogue.controller;

import com.entreprise.catalogue.dto.DepartmentDTO;
import com.entreprise.catalogue.service.DepartmentService;
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

    /**
     * GET /departments - Liste tous les départements
     */
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    /**
     * GET /departments/{id} - Affiche un département spécifique
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        Optional<DepartmentDTO> department = departmentService.getDepartmentById(id);
        return department.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /departments - Crée un nouveau département
     */
    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        try {
            DepartmentDTO createdDepartment = departmentService.createDepartment(departmentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDepartment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }

    /**
     * PUT /departments/{id} - Modifie un département existant
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentDTO departmentDTO) {
        try {
            Optional<DepartmentDTO> updatedDepartment = departmentService.updateDepartment(id, departmentDTO);
            return updatedDepartment.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }

    /**
     * DELETE /departments/{id} - Supprime un département
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        try {
            boolean deleted = departmentService.deleteDepartment(id);
            if (deleted) {
                return ResponseEntity.ok().body("Département supprimé avec succès");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }

    /**
     * GET /departments/search?name={name} - Recherche un département par nom
     */
    @GetMapping("/search")
    public ResponseEntity<DepartmentDTO> getDepartmentByName(@RequestParam String name) {
        Optional<DepartmentDTO> department = departmentService.getDepartmentByName(name);
        return department.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 