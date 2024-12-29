package com.conference.api.controllers;

import com.conference.api.dto.UserRoleDTO;
import com.conference.api.entities.UserRole;
import com.conference.api.services.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public List<UserRoleDTO> getAllUserRoles() {
        return userRoleService.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO> getUserRoleById(@PathVariable Long id) {
        return userRoleService.findById(id)
                .map(this::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserRoleDTO> createUserRole(@RequestBody UserRoleDTO userRoleDTO) {
        UserRole createdRole = userRoleService.save(toEntity(userRoleDTO));
        return ResponseEntity.status(201).body(toDTO(createdRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRoleDTO> updateUserRole(@PathVariable Long id, @RequestBody UserRoleDTO userRoleDTO) {
        UserRole updatedRole = userRoleService.update(id, toEntity(userRoleDTO));
        return ResponseEntity.ok(toDTO(updatedRole));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable Long id) {
        userRoleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private UserRoleDTO toDTO(UserRole userRole) {
        UserRoleDTO dto = new UserRoleDTO();
        dto.setId(userRole.getId());
        dto.setRole(userRole.getRole());
        dto.setUtilisateurId((long) userRole.getUtilisateur().getId());
        dto.setConferenceId((long) userRole.getConference().getId());
        return dto;
    }

    private UserRole toEntity(UserRoleDTO dto) {
        UserRole userRole = new UserRole();
        userRole.setId(dto.getId());
        userRole.setRole(dto.getRole());
        // Set `Utilisateur` and `Conference` from their respective services/repositories
        return userRole;
    }
}
