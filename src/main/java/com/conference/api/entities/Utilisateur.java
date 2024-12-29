package com.conference.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String prenom;
    private String email;

    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "utilisateur_id"))
    @Column(name = "role")
    private Set<Role> roles;

    @OneToMany(mappedBy = "createur", cascade = CascadeType.ALL)
    private List<Conference> conferencesCreees;

    @OneToMany(mappedBy = "auteur", cascade = CascadeType.ALL)
    private List<Soumission> soumissionsCreees;

    @ManyToMany
    @JoinTable(
            name = "soumission_evaluateurs",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "soumission_id")
    )
    private List<Soumission> soumissionsEvaluees;

    public boolean peutEtreEvaluateur(Soumission soumission) {
        return aLeRole(Role.EVALUATEUR) && !soumission.estCoAuteur(this);
    }

    public List<Soumission> getSoumissionsParUtilisateur(Role role) {
        if (role == Role.AUTEUR) {
            return soumissionsCreees;
        } else if (role == Role.EVALUATEUR) {
            return soumissionsEvaluees;
        }
        return List.of();
    }

    public boolean aLeRole(Role role) {
        return roles != null && roles.contains(role);
    }
}
