package com.patientservice.service;

import com.patientservice.dto.PatientRequestDTO;
import com.patientservice.dto.PatientResponseDTO;
import com.patientservice.exception.EmailAlreadyExistsException;
import com.patientservice.exception.PatientNotFoundException;
import com.patientservice.mapper.PatientMapper;
import com.patientservice.model.Patient;
import com.patientservice.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("A patient with this email exists " +
                    patientRequestDTO.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(UUID id,
                                            PatientRequestDTO patientRequestDTO){

        Patient patient=patientRepository.findById(id)
                .orElseThrow(()->new PatientNotFoundException(
                        "Patient not found with  id {}" + id));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(),id)){
            throw new EmailAlreadyExistsException("A patient with this email exists " +
                    patientRequestDTO.getEmail());
        }

        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient=patientRepository.save(patient);

        return PatientMapper.toDTO(updatedPatient);

    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }

}
