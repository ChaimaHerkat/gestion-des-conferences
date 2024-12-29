package com.conference.api.controllers;

import com.conference.api.dto.ConferenceDTO;
import com.conference.api.dto.SoumissionDTO;
import com.conference.api.entities.Conference;
import com.conference.api.entities.ConferenceEtat;
import com.conference.api.entities.Soumission;
import com.conference.api.services.ConferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


import java.util.List;
import java.util.stream.Collectors;
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "404", description = "Not Found")
})

@RestController
@RequestMapping("/conferences")
public class ConferenceController {

    private final ConferenceService conferenceService;
    private final ModelMapper modelMapper;

    // Constructor injection (recommended approach)
    public ConferenceController(ConferenceService conferenceService, ModelMapper modelMapper) {
        this.conferenceService = conferenceService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Conference> getAllConferences() {
        return conferenceService.getAllConferences();
    }

    @GetMapping("/{id}")
    public Conference getConferenceById(@PathVariable int id) {
        return conferenceService.getConferenceById(id);
    }

    @PostMapping
    public Conference createConference(@RequestBody Conference conference) {
        return conferenceService.createConference(conference);
    }

    @PutMapping("/{id}")
    public Conference updateConference(@PathVariable int id, @RequestBody Conference conference) {
        return conferenceService.updateConference(id, conference);
    }

    @DeleteMapping("/{id}")
    public void deleteConference(@PathVariable int id) {
        conferenceService.deleteConference(id);
    }

    @PutMapping("/{id}/state")
    public ResponseEntity<ConferenceDTO> changeConferenceState(
            @PathVariable int id,
            @RequestBody ConferenceEtat newState) {

        Conference updatedConference = conferenceService.changeConferenceState(id, newState);
        ConferenceDTO conferenceDTO = modelMapper.map(updatedConference, ConferenceDTO.class);
        return ResponseEntity.ok(conferenceDTO);
    }

    @GetMapping("/{id}/soumissions")
    public ResponseEntity<List<SoumissionDTO>> getSoumissionsByConferenceId(@PathVariable int id) {
        // Retrieve list of Soumissions for the given conference ID
        List<Soumission> soumissions = conferenceService.getSoumissionsByConferenceId(id);

        // Convert Soumission entities to SoumissionDTO using ModelMapper
        List<SoumissionDTO> soumissionDTOs = soumissions.stream()
                .map(soumission -> modelMapper.map(soumission, SoumissionDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(soumissionDTOs);
    }
}
