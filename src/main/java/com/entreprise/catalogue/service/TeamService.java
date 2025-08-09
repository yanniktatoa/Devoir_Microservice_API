package com.entreprise.catalogue.service;

import com.entreprise.catalogue.dto.TeamDTO;
import com.entreprise.catalogue.entity.Department;
import com.entreprise.catalogue.entity.Team;
import com.entreprise.catalogue.repository.DepartmentRepository;
import com.entreprise.catalogue.repository.TeamRepository;
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
    private DepartmentRepository departmentRepository;

    /**
     * Récupère toutes les équipes
     */
    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Récupère une équipe par son ID
     */
    public Optional<TeamDTO> getTeamById(Long id) {
        return teamRepository.findById(id)
                .map(this::convertToDTO);
    }

    /**
     * Récupère toutes les équipes d'un département
     */
    public List<TeamDTO> getTeamsByDepartmentId(Long departmentId) {
        return teamRepository.findByDepartmentIdWithDepartment(departmentId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Crée une nouvelle équipe
     */
    public TeamDTO createTeam(TeamDTO teamDTO) {
        // Vérifier si le département existe
        Department department = departmentRepository.findById(teamDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Département non trouvé avec l'ID: " + teamDTO.getDepartmentId()));

        // Vérifier si une équipe avec le même nom existe déjà dans ce département
        if (teamRepository.existsByTeamNameAndDepartmentId(teamDTO.getTeamName(), teamDTO.getDepartmentId())) {
            throw new RuntimeException("Une équipe avec le nom '" + teamDTO.getTeamName() + "' existe déjà dans ce département");
        }

        Team team = new Team();
        team.setTeamName(teamDTO.getTeamName());
        team.setDescription(teamDTO.getDescription());
        team.setDepartment(department);

        Team savedTeam = teamRepository.save(team);
        return convertToDTO(savedTeam);
    }

    /**
     * Met à jour une équipe
     */
    public Optional<TeamDTO> updateTeam(Long id, TeamDTO teamDTO) {
        return teamRepository.findById(id)
                .map(existingTeam -> {
                    // Vérifier si le département existe
                    Department department = departmentRepository.findById(teamDTO.getDepartmentId())
                            .orElseThrow(() -> new RuntimeException("Département non trouvé avec l'ID: " + teamDTO.getDepartmentId()));

                    // Vérifier si une équipe avec le même nom existe déjà dans ce département (sauf l'équipe actuelle)
                    if (!existingTeam.getTeamName().equals(teamDTO.getTeamName()) &&
                        teamRepository.existsByTeamNameAndDepartmentId(teamDTO.getTeamName(), teamDTO.getDepartmentId())) {
                        throw new RuntimeException("Une équipe avec le nom '" + teamDTO.getTeamName() + "' existe déjà dans ce département");
                    }

                    existingTeam.setTeamName(teamDTO.getTeamName());
                    existingTeam.setDescription(teamDTO.getDescription());
                    existingTeam.setDepartment(department);

                    Team updatedTeam = teamRepository.save(existingTeam);
                    return convertToDTO(updatedTeam);
                });
    }

    /**
     * Supprime une équipe
     */
    public boolean deleteTeam(Long id) {
        if (teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Convertit une entité Team en DTO
     */
    private TeamDTO convertToDTO(Team team) {
        return new TeamDTO(
                team.getId(),
                team.getTeamName(),
                team.getDescription(),
                team.getDepartment().getId(),
                team.getDepartment().getName()
        );
    }

    /**
     * Recherche des équipes par nom
     */
    public List<TeamDTO> searchTeamsByName(String teamName) {
        return teamRepository.findByTeamNameContainingIgnoreCase(teamName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
} 