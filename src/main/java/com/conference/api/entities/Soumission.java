package com.conference.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Soumission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titreArticle;
    private String resume;
    private String documentPdf;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSoumission;

    private String etat;

    // Relation avec Conference
    @ManyToOne
    @JoinColumn(name = "conference_id", nullable = false) // Update to match DB column
    private Conference conference;

    // Relation avec l'auteur principal
    @ManyToOne
    @JoinColumn(name = "auteur_id", nullable = false) // Update to match DB column
    private Utilisateur auteur;

    // Relation Many-to-Many pour les co-auteurs
    @ManyToMany
    @JoinTable(
            name = "soumission_auteurs", // Update table name if needed
            joinColumns = @JoinColumn(name = "soumission_id"), // Update if DB column differs
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id") // Update if DB column differs
    )
    private List<Utilisateur> coAuteurs;

    // Relation Many-to-Many pour les évaluateurs
    @ManyToMany
    @JoinTable(
            name = "soumission_evaluateurs", // Update table name if needed
            joinColumns = @JoinColumn(name = "soumission_id"), // Update if DB column differs
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id") // Update if DB column differs
    )
    private List<Utilisateur> evaluateurs;

    // --- Méthodes Métiers ---

    public void ajouterCoAuteur(Utilisateur coAuteur) {
        if (coAuteurs != null && !coAuteurs.contains(coAuteur)) {
            coAuteurs.add(coAuteur);
        }
    }

    public boolean estCoAuteur(Utilisateur utilisateur) {
        return coAuteurs != null && coAuteurs.contains(utilisateur);
    }
}
