package com.entreprise.catalogue.service;

import com.entreprise.catalogue.dto.DepartmentDTO;
import com.entreprise.catalogue.entity.Department;
import com.entreprise.catalogue.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * Récupère tous les départements
     */
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupère un département par son ID
     */
    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Crée un nouveau département
     */
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        // Vérifier si le nom existe déjà
        if (departmentRepository.existsByName(departmentDTO.getName())) {
            throw new RuntimeException("Un département avec le nom '" + departmentDTO.getName() + "' existe déjà");
        }

        Department department = new Department();
        department.setName(departmentDTO.getName());
        
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }

    /**
     * Met à jour un département
     */
    public Optional<DepartmentDTO> updateDepartment(Long id, DepartmentDTO departmentDTO) {
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    // Vérifier si le nouveau nom existe déjà (sauf pour le département actuel)
                    if (!existingDepartment.getName().equals(departmentDTO.getName()) &&
                        departmentRepository.existsByName(departmentDTO.getName())) {
                        throw new RuntimeException("Un département avec le nom '" + departmentDTO.getName() + "' existe déjà");
                    }
                    
                    existingDepartment.setName(departmentDTO.getName());
                    Department updatedDepartment = departmentRepository.save(existingDepartment);
                    return convertToDTO(updatedDepartment);
                });
    }

    /**
     * Supprime un département
     */
    public boolean deleteDepartment(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            // Vérifier si le département a des équipes
            if (!department.get().getTeams().isEmpty()) {
                throw new RuntimeException("Impossible de supprimer le département car il contient des équipes");
            }
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Convertit une entité Department en DTO
     */
    private DepartmentDTO convertToDTO(Department department) {
        return new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getTeams().size()
        );
    }

    /**
     * Récupère un département par son nom
     */
    public Optional<DepartmentDTO> getDepartmentByName(String name) {
        return departmentRepository.findByName(name)
                .map(this::convertToDTO);
    }
} 