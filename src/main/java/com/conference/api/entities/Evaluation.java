package com.conference.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int note;
    private String commentaires;

    @Enumerated(EnumType.STRING)
    private EvaluationEtat etat; // ACCEPTÉE, REJETÉE, EN_REVISION

    @ManyToOne
    private Soumission soumission;

    @ManyToOne
    private Utilisateur evaluateur;

    private Date dateEvaluation;

    // --- Méthodes métier ---

    /**
     * Ajoute une évaluation à une soumission par un évaluateur.
     *
     * @param evaluateur    L'évaluateur effectuant l'évaluation.
     * @param note          La note attribuée.
     * @param commentaires  Les commentaires de l'évaluateur.
     */
    public void ajouterEvaluation(Utilisateur evaluateur, int note, String commentaires) {
        if (this.evaluateur != null && !this.evaluateur.equals(evaluateur)) {
            throw new IllegalStateException("L'utilisateur fourni n'est pas assigné comme évaluateur pour cette soumission.");
        }
        this.note = note;
        this.commentaires = commentaires;
        this.dateEvaluation = new Date();
        this.etat = EvaluationEtat.EN_REVISION;
    }

    /**
     * Vérifie si une évaluation est permise en fonction de la date limite.
     *
     * @param dateLimite La date limite.
     * @return true si l'évaluation est dans les limites, sinon false.
     */
    public boolean estEvaluationPermise(Date dateLimite) {
        return dateEvaluation == null || dateEvaluation.before(dateLimite);
    }

    /**
     * Vérifie si l'état cible est permis pour une réévaluation.
     *
     * @param nouvelEtat Le nouvel état à appliquer.
     * @return true si l'état est valide pour une réévaluation, sinon false.
     */
    public boolean estReevaluationPermise(EvaluationEtat nouvelEtat) {
        return this.etat == EvaluationEtat.EN_REVISION &&
                (nouvelEtat == EvaluationEtat.ACCEPTEE || nouvelEtat == EvaluationEtat.REJETEE);
    }
}
