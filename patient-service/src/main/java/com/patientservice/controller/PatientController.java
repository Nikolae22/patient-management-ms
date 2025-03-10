package com.patientservice.controller;

import com.patientservice.dto.PatientRequestDTO;
import com.patientservice.dto.PatientResponseDTO;
import com.patientservice.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;


    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        return ResponseEntity.ok().body(patientService.getPanties());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@RequestBody @Valid
                                                                PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDTO));
    }
}
