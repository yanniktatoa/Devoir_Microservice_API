package com.entreprise.catalogue.department.service;

import com.entreprise.catalogue.department.dto.DepartmentDTO;
import com.entreprise.catalogue.department.entity.Department;
import com.entreprise.catalogue.department.repository.DepartmentRepository;
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
    
    // Récupérer tous les départements
    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        System.out.println("Nombre de départements trouvés: " + departments.size());
        departments.forEach(dept -> System.out.println("Département: " + dept.getName()));
        
        return departments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Récupérer un département par ID
    public Optional<DepartmentDTO> getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    // Récupérer un département par nom
    public Optional<DepartmentDTO> getDepartmentByName(String name) {
        return departmentRepository.findByName(name)
                .map(this::convertToDTO);
    }
    
    // Rechercher des départements par nom
    public List<DepartmentDTO> searchDepartmentsByName(String name) {
        return departmentRepository.findByNameContaining(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Créer un nouveau département
    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        if (departmentRepository.existsByName(departmentDTO.getName())) {
            throw new RuntimeException("Un département avec ce nom existe déjà");
        }
        
        Department department = new Department(departmentDTO.getName());
        Department savedDepartment = departmentRepository.save(department);
        return convertToDTO(savedDepartment);
    }
    
    // Mettre à jour un département
    public Optional<DepartmentDTO> updateDepartment(Long id, DepartmentDTO departmentDTO) {
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    // Vérifier si le nouveau nom existe déjà (sauf pour le département actuel)
                    if (!existingDepartment.getName().equals(departmentDTO.getName()) &&
                        departmentRepository.existsByName(departmentDTO.getName())) {
                        throw new RuntimeException("Un département avec ce nom existe déjà");
                    }
                    
                    existingDepartment.setName(departmentDTO.getName());
                    Department updatedDepartment = departmentRepository.save(existingDepartment);
                    return convertToDTO(updatedDepartment);
                });
    }
    
    // Supprimer un département
    public boolean deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Compter le nombre de départements
    public long countDepartments() {
        return departmentRepository.countDepartments();
    }
    
    // Vérifier si un département existe
    public boolean departmentExists(Long id) {
        return departmentRepository.existsById(id);
    }
    
    // Conversion Entity vers DTO
    private DepartmentDTO convertToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setCreatedAt(department.getCreatedAt());
        dto.setUpdatedAt(department.getUpdatedAt());
        return dto;
    }
} 