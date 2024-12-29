package com.conference.api.services;

import com.conference.api.entities.UserRole;
import com.conference.api.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> findAll() {
        return userRoleRepository.findAll();
    }

    public Optional<UserRole> findById(Long id) {
        return userRoleRepository.findById(id);
    }

    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    public UserRole update(Long id, UserRole userRole) {
        UserRole existingRole = userRoleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Role with ID " + id + " not found"));

        existingRole.setRole(userRole.getRole());
        existingRole.setUtilisateur(userRole.getUtilisateur());
        existingRole.setConference(userRole.getConference());
        return userRoleRepository.save(existingRole);
    }

    public void deleteById(Long id) {
        if (!userRoleRepository.existsById(id)) {
            throw new IllegalArgumentException("Role with ID " + id + " not found");
        }
        userRoleRepository.deleteById(id);
    }
}
