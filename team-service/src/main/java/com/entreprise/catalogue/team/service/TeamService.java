package com.entreprise.catalogue.team.service;

import com.entreprise.catalogue.team.client.DepartmentClient;
import com.entreprise.catalogue.team.dto.TeamDTO;
import com.entreprise.catalogue.team.entity.Team;
import com.entreprise.catalogue.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private DepartmentClient departmentClient;
    
    // Récupérer toutes les équipes
    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Récupérer une équipe par ID
    public Optional<TeamDTO> getTeamById(Long id) {
        return teamRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    // Récupérer les équipes d'un département
    public List<TeamDTO> getTeamsByDepartment(Long departmentId) {
        return teamRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Rechercher des équipes par nom
    public List<TeamDTO> searchTeamsByName(String name) {
        return teamRepository.findByNameContaining(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Créer une nouvelle équipe
    public TeamDTO createTeam(TeamDTO teamDTO) {
        // Vérifier si le département existe
        try {
            DepartmentClient.DepartmentDTO department = departmentClient.getDepartmentById(teamDTO.getDepartmentId());
            if (department == null) {
                throw new RuntimeException("Le département spécifié n'existe pas");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la vérification du département: " + e.getMessage());
        }
        
        if (teamRepository.existsByName(teamDTO.getName())) {
            throw new RuntimeException("Une équipe avec ce nom existe déjà");
        }
        
        Team team = new Team(teamDTO.getName(), teamDTO.getDescription(), teamDTO.getDepartmentId());
        Team savedTeam = teamRepository.save(team);
        return convertToDTO(savedTeam);
    }
    
    // Mettre à jour une équipe
    public Optional<TeamDTO> updateTeam(Long id, TeamDTO teamDTO) {
        return teamRepository.findById(id)
                .map(existingTeam -> {
                    // Vérifier si le département existe
                    try {
                        DepartmentClient.DepartmentDTO department = departmentClient.getDepartmentById(teamDTO.getDepartmentId());
                        if (department == null) {
                            throw new RuntimeException("Le département spécifié n'existe pas");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Erreur lors de la vérification du département: " + e.getMessage());
                    }
                    
                    // Vérifier si le nouveau nom existe déjà (sauf pour l'équipe actuelle)
                    if (!existingTeam.getName().equals(teamDTO.getName()) &&
                        teamRepository.existsByName(teamDTO.getName())) {
                        throw new RuntimeException("Une équipe avec ce nom existe déjà");
                    }
                    
                    existingTeam.setName(teamDTO.getName());
                    existingTeam.setDescription(teamDTO.getDescription());
                    existingTeam.setDepartmentId(teamDTO.getDepartmentId());
                    
                    Team updatedTeam = teamRepository.save(existingTeam);
                    return convertToDTO(updatedTeam);
                });
    }
    
    // Supprimer une équipe
    public boolean deleteTeam(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Compter le nombre d'équipes
    public long countTeams() {
        return teamRepository.countTeams();
    }
    
    // Compter le nombre d'équipes par département
    public long countTeamsByDepartment(Long departmentId) {
        return teamRepository.countByDepartmentId(departmentId);
    }
    
    // Conversion Entity vers DTO
    private TeamDTO convertToDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setDescription(team.getDescription());
        dto.setDepartmentId(team.getDepartmentId());
        dto.setCreatedAt(team.getCreatedAt());
        dto.setUpdatedAt(team.getUpdatedAt());
        
        // Récupérer le nom du département via le client Feign
        try {
            DepartmentClient.DepartmentDTO department = departmentClient.getDepartmentById(team.getDepartmentId());
            if (department != null) {
                dto.setDepartmentName(department.getName());
            }
        } catch (Exception e) {
            // En cas d'erreur, on ne met pas le nom du département
            dto.setDepartmentName("Département non disponible");
        }
        
        return dto;
    }
} 