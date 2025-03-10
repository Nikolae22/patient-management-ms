package com.patientservice.controller;

import com.patientservice.dto.PatientRequestDTO;
import com.patientservice.dto.PatientResponseDTO;
import com.patientservice.dto.validators.CreatePatientValidationGroup;
import com.patientservice.service.PatientService;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<PatientResponseDTO> createPatient(
            @RequestBody @Validated({Default.class,
                    CreatePatientValidationGroup.class})
            PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
                                                            @RequestBody @Validated({Default.class})
                                                            PatientRequestDTO patientRequestDTO){

        PatientResponseDTO patientResponseDTO=patientService.updatePatient(id,patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    @DeleteMapping("/{id]")
    public ResponseEntity<String> deletePatientById(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
