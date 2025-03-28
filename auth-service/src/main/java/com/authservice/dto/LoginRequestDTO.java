package com.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDTO {

    @NotBlank(message = "Email is Required")
    @Email(message = "Valid email pls")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8,message = "Password at least 8ch")
    private String password;

    public @NotBlank(message = "Email is Required") @Email(message = "Valid email pls") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is Required") @Email(message = "Valid email pls") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Password is required") @Size(min = 8, message = "Password at least 8ch") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password is required") @Size(min = 8, message = "Password at least 8ch") String password) {
        this.password = password;
    }
}
