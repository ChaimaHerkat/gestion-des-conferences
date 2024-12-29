package com.conference.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.conference.api.entities.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
    // Ajoutez des requêtes spécifiques comme :
    // List<Evaluation> findByEtat(String etat);
}
