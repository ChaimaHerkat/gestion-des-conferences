package com.conference.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@Data
@NoArgsConstructor
public class SoumissionDTO {
    private String titreArticle;
    private String resume;
    private String etat;
    private LocalDateTime dateSoumission;
    private List<Integer> coAuteurs; // IDs des co-auteurs
    private List<Integer> evaluateurs; // IDs des évaluateurs
}
