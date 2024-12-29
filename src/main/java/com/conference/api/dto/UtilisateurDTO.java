package com.conference.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UtilisateurDTO {
    private String nom;
    private String prenom;
    private String email;
    private Set<String> roles; // List of roles (e.g., EDITEUR, AUTEUR, EVALUATEUR)
}
