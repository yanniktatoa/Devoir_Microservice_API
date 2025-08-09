package com.entreprise.catalogue.controller;

import com.entreprise.catalogue.dto.TeamDTO;
import com.entreprise.catalogue.service.TeamService;
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

    /**
     * GET /teams - Liste toutes les équipes
     */
    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    /**
     * GET /teams/{id} - Affiche une équipe spécifique
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        Optional<TeamDTO> team = teamService.getTeamById(id);
        return team.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * GET /teams/by-department/{departmentId} - Liste les équipes d'un département
     */
    @GetMapping("/by-department/{departmentId}")
    public ResponseEntity<List<TeamDTO>> getTeamsByDepartmentId(@PathVariable Long departmentId) {
        List<TeamDTO> teams = teamService.getTeamsByDepartmentId(departmentId);
        return ResponseEntity.ok(teams);
    }

    /**
     * POST /teams - Crée une nouvelle équipe
     */
    @PostMapping
    public ResponseEntity<?> createTeam(@Valid @RequestBody TeamDTO teamDTO) {
        try {
            TeamDTO createdTeam = teamService.createTeam(teamDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }

    /**
     * PUT /teams/{id} - Modifie une équipe
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable Long id, @Valid @RequestBody TeamDTO teamDTO) {
        try {
            Optional<TeamDTO> updatedTeam = teamService.updateTeam(id, teamDTO);
            return updatedTeam.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erreur: " + e.getMessage());
        }
    }

    /**
     * DELETE /teams/{id} - Supprime une équipe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        boolean deleted = teamService.deleteTeam(id);
        if (deleted) {
            return ResponseEntity.ok().body("Équipe supprimée avec succès");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /teams/search?name={name} - Recherche des équipes par nom
     */
    @GetMapping("/search")
    public ResponseEntity<List<TeamDTO>> searchTeamsByName(@RequestParam String name) {
        List<TeamDTO> teams = teamService.searchTeamsByName(name);
        return ResponseEntity.ok(teams);
    }
} 