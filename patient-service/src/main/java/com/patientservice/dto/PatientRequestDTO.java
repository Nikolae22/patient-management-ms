package com.patientservice.dto;

import com.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


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

    public @NotBlank(message = "Required") @Size(max = 100, message = "Nome cannot exceed 100 ch") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Required") @Size(max = 100, message = "Nome cannot exceed 100 ch") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Is required") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Date is required") String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "Date is required") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public @NotBlank(groups = CreatePatientValidationGroup.class,
            message = "Registered date is Required") String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(@NotBlank(groups = CreatePatientValidationGroup.class,
            message = "Registered date is Required") String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
