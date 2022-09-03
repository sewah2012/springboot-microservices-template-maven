package com.sewah.usersservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSignupDto {
    @NotBlank(message = "First name cannot be empty or blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty or blank")
    private String lastName;
    @Email(message="Please provide a valid email address")
    private String email;
    @NotBlank(message = "Password cannot be empty or blank")
    @Size(min = 7, message="Password must be at least 7 characters long")
    private String password;

    private String dateOfBirth;
}
