package com.patientservice.dto;

import com.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {

    @NotBlank(message = "Required")
    @Size(max = 100,message = "Nome cannot exceed 100 ch")
    private String name;

    @NotBlank(message = "Required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Is required")
    private String address;

    @NotBlank(message = "Date is required")
    private String dateOfBirth;


    @NotBlank(groups = CreatePatientValidationGroup.class,
            message = "Registered date is Required")
    private String registeredDate;



}
