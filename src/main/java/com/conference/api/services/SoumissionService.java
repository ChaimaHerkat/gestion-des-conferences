package com.conference.api.services;

import com.conference.api.dto.SoumissionDTO;
import com.conference.api.entities.Soumission;
import com.conference.api.entities.Utilisateur;
import com.conference.api.entities.Role;
import com.conference.api.repositories.SoumissionRepository;
import com.conference.api.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SoumissionService {

    private final SoumissionRepository soumissionRepository;
    private final UtilisateurRepository utilisateurRepository;

    public SoumissionService(SoumissionRepository soumissionRepository, UtilisateurRepository utilisateurRepository) {
        this.soumissionRepository = soumissionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Soumission createSoumission(SoumissionDTO soumissionDTO) {
        Soumission soumission = new Soumission();
        soumission.setTitreArticle(soumissionDTO.getTitreArticle());
        soumission.setResume(soumissionDTO.getResume());
        soumission.setEtat(soumissionDTO.getEtat());
        return soumissionRepository.save(soumission);
    }

    public Soumission assignEvaluateur(int soumissionId, int evaluateurId) {
        Soumission soumission = soumissionRepository.findById(soumissionId)
                .orElseThrow(() -> new RuntimeException("Soumission not found"));

        Utilisateur evaluateur = utilisateurRepository.findById(evaluateurId)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));

        if (!evaluateur.aLeRole(Role.EVALUATEUR)) {
            throw new IllegalArgumentException("Utilisateur n'est pas un évaluateur");
        }

        if (!soumission.getEvaluateurs().contains(evaluateur)) {
            soumission.getEvaluateurs().add(evaluateur);
            return soumissionRepository.save(soumission);
        }

        throw new RuntimeException("Évaluateur déjà assigné");
    }

    public List<Soumission> getSoumissionsByConference(int conferenceId) {
        return soumissionRepository.findByConferenceId(conferenceId);
    }

    public List<Soumission> getSoumissionsByAuteur(int auteurId) {
        return soumissionRepository.findByAuteurId(auteurId);
    }

    public List<Soumission> getSoumissionsByEtat(String etat) {
        return soumissionRepository.findByEtat(etat);
    }
}
