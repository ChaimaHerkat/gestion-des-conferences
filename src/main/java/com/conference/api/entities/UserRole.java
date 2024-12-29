package com.conference.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER_ROLES")
@Getter
@Setter
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String role; // Possible values: EDITEUR, AUTEUR, EVALUATEUR

    @ManyToOne
    @JoinColumn(name = "UTILISATEUR_ID", nullable = false)
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "CONFERENCE_ID", nullable = false)
    private Conference conference;

    // --- Additional Methods (Optional) ---

    /**
     * Constructor with parameters.
     * @param role The role of the user in the conference.
     * @param utilisateur The user associated with the role.
     * @param conference The conference associated with the role.
     */
    public UserRole(String role, Utilisateur utilisateur, Conference conference) {
        this.role = role;
        this.utilisateur = utilisateur;
        this.conference = conference;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", utilisateur=" + utilisateur.getId() +
                ", conference=" + conference.getId() +
                '}';
    }
}
