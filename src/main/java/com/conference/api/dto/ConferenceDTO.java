package com.conference.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ConferenceDTO {
    private String titre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String thematique;
    private String etat;
    private List<Integer> soumissions; // IDs of submissions
    private Integer createurId; // ID of the creator
}
