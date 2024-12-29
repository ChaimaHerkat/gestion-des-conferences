package com.conference.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.conference.api.entities.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
    // Ajoutez des requêtes spécifiques si besoin
    // List<Conference> findByThematique(String thematique);
}
