package com.patientservice.service;

import com.patientservice.dto.PatientRequestDTO;
import com.patientservice.dto.PatientResponseDTO;
import com.patientservice.mapper.PatientMapper;
import com.patientservice.model.Patient;
import com.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;


    public List<PatientResponseDTO> getPanties() {
        List<Patient> patients = patientRepository.findAll();

        List<PatientResponseDTO> patientResponseDTOS =
                patients.stream().map(PatientMapper::toDTO)
                        .toList();
        return patientResponseDTOS;
    }


    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(patient);
    }

}
