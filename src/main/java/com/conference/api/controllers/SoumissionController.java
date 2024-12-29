package com.conference.api.controllers;

import com.conference.api.dto.SoumissionDTO;
import com.conference.api.entities.Soumission;
import com.conference.api.services.SoumissionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soumissions")
public class SoumissionController {

    private final SoumissionService soumissionService;
    private final ModelMapper modelMapper;

    public SoumissionController(SoumissionService soumissionService, ModelMapper modelMapper) {
        this.soumissionService = soumissionService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Soumission> createSoumission(@RequestBody SoumissionDTO soumissionDTO) {
        Soumission newSoumission = soumissionService.createSoumission(soumissionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSoumission);
    }

    @PostMapping("/{id}/evaluateurs/{evaluateurId}")
    public ResponseEntity<SoumissionDTO> assignEvaluateur(
            @PathVariable int id,
            @PathVariable int evaluateurId) {
        Soumission updatedSoumission = soumissionService.assignEvaluateur(id, evaluateurId);
        SoumissionDTO soumissionDTO = modelMapper.map(updatedSoumission, SoumissionDTO.class);
        return ResponseEntity.ok(soumissionDTO);
    }

    @GetMapping("/conference/{conferenceId}")
    public ResponseEntity<List<Soumission>> getSoumissionsByConference(@PathVariable int conferenceId) {
        return ResponseEntity.ok(soumissionService.getSoumissionsByConference(conferenceId));
    }

    @GetMapping("/auteur/{auteurId}")
    public ResponseEntity<List<Soumission>> getSoumissionsByAuteur(@PathVariable int auteurId) {
        return ResponseEntity.ok(soumissionService.getSoumissionsByAuteur(auteurId));
    }

    @GetMapping("/etat/{etat}")
    public ResponseEntity<List<Soumission>> getSoumissionsByEtat(@PathVariable String etat) {
        return ResponseEntity.ok(soumissionService.getSoumissionsByEtat(etat));
    }
}
