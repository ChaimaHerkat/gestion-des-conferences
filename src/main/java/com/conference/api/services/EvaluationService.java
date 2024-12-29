package com.conference.api.services;

import org.springframework.stereotype.Service;
import java.util.List;
import com.conference.api.entities.Evaluation;
import com.conference.api.repositories.EvaluationRepository;
import com.conference.api.entities.EvaluationEtat;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    // Injection via constructeur
    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Evaluation getEvaluationById(int id) {
        return evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found with id " + id));
    }

    public Evaluation createEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    public Evaluation updateEvaluation(int id, Evaluation evaluationDetails) {
        Evaluation evaluation = getEvaluationById(id);
        evaluation.setNote(evaluationDetails.getNote());
        evaluation.setCommentaires(evaluationDetails.getCommentaires());
        evaluation.setEtat(evaluationDetails.getEtat());
        return evaluationRepository.save(evaluation);
    }

    public void deleteEvaluation(int id) {
        Evaluation evaluation = getEvaluationById(id);
        evaluationRepository.delete(evaluation);
    }

    public Evaluation changeEvaluationState(int evaluationId, EvaluationEtat newState) {
        Evaluation evaluation = evaluationRepository.findById(evaluationId)
                .orElseThrow(() -> new RuntimeException("Evaluation not found"));

        evaluation.setEtat(newState);
        return evaluationRepository.save(evaluation);
    }
}
