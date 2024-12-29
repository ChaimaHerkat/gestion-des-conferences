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
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;
    private Date dateDebut;
    private Date dateFin;
    private String thematique;

    @Enumerated(EnumType.STRING)
    private ConferenceEtat etat; // OUVERTE, EN_REVUE, FERMEE

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
    private List<Soumission> soumissions;

    @ManyToOne
    private Utilisateur createur;

    public void ouvrirSoumissions() {
        if (etat != null && etat != ConferenceEtat.FERMEE) {
            this.etat = ConferenceEtat.OUVERTE;
        } else {
            throw new IllegalStateException("Une conférence fermée ne peut pas être réouverte.");
        }
    }

    public void fermerSoumissions() {
        if (etat == ConferenceEtat.OUVERTE) {
            this.etat = ConferenceEtat.FERMEE;
        } else {
            throw new IllegalStateException("Seules les conférences ouvertes peuvent être fermées.");
        }
    }

    public void passerEnRevue() {
        if (etat == ConferenceEtat.OUVERTE) {
            this.etat = ConferenceEtat.EN_REVUE;
        } else {
            throw new IllegalStateException("Seules les conférences ouvertes peuvent passer en revue.");
        }
    }

    public boolean verifierCreateur(Utilisateur editeur) {
        return editeur != null && editeur.equals(this.createur);
    }

    public boolean estTransitionPermise(ConferenceEtat etatSource, ConferenceEtat etatDestination) {
        return etatSource == ConferenceEtat.EN_REVUE && etatDestination == ConferenceEtat.FERMEE;
    }

    public boolean estActionPermise(ConferenceEtat etatCible) {
        return this.etat == etatCible;
    }

    public List<Soumission> genererRapportSoumissions() {
        return this.soumissions;
    }
}
