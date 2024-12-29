package com.conference.api.repositories;

import com.conference.api.entities.Utilisateur;
import com.conference.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    // Trouver un utilisateur par son email
    Optional<Utilisateur> findByEmail(String email);

    // Trouver tous les utilisateurs ayant un rôle spécifique
    List<Utilisateur> findByRoles(Role role);
}
