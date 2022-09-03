package com.sewah.usersservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDto {
    @NotBlank(message = "Email must be provided")
    @Email(message="Email must be a valid email address")
    private String email;
    @NotBlank(message = "password cannot be empty or blank")
    private String password;
}
