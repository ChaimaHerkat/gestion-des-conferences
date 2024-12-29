package com.conference.api.controllers;

import com.conference.api.dto.EvaluationDTO;
import com.conference.api.entities.Evaluation;
import com.conference.api.entities.EvaluationEtat;
import com.conference.api.services.EvaluationService;
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
@RequestMapping("/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;
    private final ModelMapper modelMapper;

    public EvaluationController(EvaluationService evaluationService, ModelMapper modelMapper) {
        this.evaluationService = evaluationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationService.getAllEvaluations()
                .stream()
                .map(evaluation -> modelMapper.map(evaluation, EvaluationDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationDTO> getEvaluationById(@PathVariable int id) {
        Evaluation evaluation = evaluationService.getEvaluationById(id);
        EvaluationDTO evaluationDTO = modelMapper.map(evaluation, EvaluationDTO.class);
        return ResponseEntity.ok(evaluationDTO);
    }

    @PostMapping
    public ResponseEntity<EvaluationDTO> createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        Evaluation evaluation = modelMapper.map(evaluationDTO, Evaluation.class);
        Evaluation createdEvaluation = evaluationService.createEvaluation(evaluation);
        EvaluationDTO createdEvaluationDTO = modelMapper.map(createdEvaluation, EvaluationDTO.class);
        return ResponseEntity.ok(createdEvaluationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationDTO> updateEvaluation(
            @PathVariable int id, @RequestBody EvaluationDTO evaluationDTO) {
        Evaluation evaluation = modelMapper.map(evaluationDTO, Evaluation.class);
        Evaluation updatedEvaluation = evaluationService.updateEvaluation(id, evaluation);
        EvaluationDTO updatedEvaluationDTO = modelMapper.map(updatedEvaluation, EvaluationDTO.class);
        return ResponseEntity.ok(updatedEvaluationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable int id) {
        evaluationService.deleteEvaluation(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/state")
    public ResponseEntity<EvaluationDTO> changeEvaluationState(
            @PathVariable int id, @RequestBody EvaluationEtat newState) {
        Evaluation updatedEvaluation = evaluationService.changeEvaluationState(id, newState);
        EvaluationDTO updatedEvaluationDTO = modelMapper.map(updatedEvaluation, EvaluationDTO.class);
        return ResponseEntity.ok(updatedEvaluationDTO);
    }
}
