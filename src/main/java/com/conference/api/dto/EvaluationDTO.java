package com.conference.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EvaluationDTO {
    private Integer note;
    private String commentaires;
    private String etat;
    private Integer soumissionId; // ID of the submission
    private Integer evaluateurId; // ID of the evaluator
    private LocalDate dateEvaluation;
}
