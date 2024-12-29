package com.conference.api.services;

import com.conference.api.entities.Conference;
import com.conference.api.entities.ConferenceEtat;
import com.conference.api.entities.Soumission;
import com.conference.api.repositories.ConferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    // Injection via le constructeur
    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    // Récupérer toutes les conférences
    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    // Récupérer une conférence par son ID
    public Conference getConferenceById(int id) {
        return conferenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conference not found with id " + id));
    }

    // Créer une nouvelle conférence
    public Conference createConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    // Mettre à jour une conférence existante
    public Conference updateConference(int id, Conference conferenceDetails) {
        Conference conference = getConferenceById(id);

        // Mise à jour des champs
        conference.setTitre(conferenceDetails.getTitre());
        conference.setThematique(conferenceDetails.getThematique());
        conference.setDateDebut(conferenceDetails.getDateDebut());
        conference.setDateFin(conferenceDetails.getDateFin());
        conference.setEtat(conferenceDetails.getEtat());

        return conferenceRepository.save(conference);
    }

    // Supprimer une conférence
    public void deleteConference(int id) {
        Conference conference = getConferenceById(id);
        conferenceRepository.delete(conference);
    }

    // Modifier l'état d'une conférence
    public Conference changeConferenceState(int conferenceId, ConferenceEtat newState) {
        Conference conference = conferenceRepository.findById(conferenceId)
                .orElseThrow(() -> new RuntimeException("Conference not found with id " + conferenceId));

        conference.setEtat(newState);
        return conferenceRepository.save(conference);
    }

    // Récupérer les soumissions d'une conférence par ID
    public List<Soumission> getSoumissionsByConferenceId(int conferenceId) {
        Conference conference = getConferenceById(conferenceId);
        return conference.getSoumissions();
    }
}
