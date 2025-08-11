package com.entreprise.catalogue.team.controller;

import com.entreprise.catalogue.team.dto.TeamDTO;
import com.entreprise.catalogue.team.service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "*")
public class TeamController {
    
    @Autowired
    private TeamService teamService;
    
    // GET /api/teams - Liste toutes les équipes
    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }
    
    // GET /api/teams/{id} - Récupère une équipe par ID
    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        Optional<TeamDTO> team = teamService.getTeamById(id);
        return team.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // GET /api/teams/by-department/{departmentId} - Équipes d'un département
    @GetMapping("/by-department/{departmentId}")
    public ResponseEntity<List<TeamDTO>> getTeamsByDepartment(@PathVariable Long departmentId) {
        List<TeamDTO> teams = teamService.getTeamsByDepartment(departmentId);
        return ResponseEntity.ok(teams);
    }
    
    // GET /api/teams/search?name={name} - Recherche par nom
    @GetMapping("/search")
    public ResponseEntity<List<TeamDTO>> searchTeamsByName(@RequestParam String name) {
        List<TeamDTO> teams = teamService.searchTeamsByName(name);
        return ResponseEntity.ok(teams);
    }
    
    // POST /api/teams - Crée une nouvelle équipe
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        try {
            TeamDTO createdTeam = teamService.createTeam(teamDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // PUT /api/teams/{id} - Met à jour une équipe
    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable Long id, 
                                            @Valid @RequestBody TeamDTO teamDTO) {
        try {
            Optional<TeamDTO> updatedTeam = teamService.updateTeam(id, teamDTO);
            return updatedTeam.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // DELETE /api/teams/{id} - Supprime une équipe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        boolean deleted = teamService.deleteTeam(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // GET /api/teams/count - Compte le nombre d'équipes
    @GetMapping("/count")
    public ResponseEntity<Long> countTeams() {
        long count = teamService.countTeams();
        return ResponseEntity.ok(count);
    }
    
    // GET /api/teams/count/by-department/{departmentId} - Compte les équipes d'un département
    @GetMapping("/count/by-department/{departmentId}")
    public ResponseEntity<Long> countTeamsByDepartment(@PathVariable Long departmentId) {
        long count = teamService.countTeamsByDepartment(departmentId);
        return ResponseEntity.ok(count);
    }
} 