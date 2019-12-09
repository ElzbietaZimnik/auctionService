package com.aukcje.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class DtoUserRegistration {

    private Long id;

    @NotEmpty(message = "To pole nie może byc puste")
    @Size(min = 3, max = 15, message = "Pole musi zawierać min 3 znaki i max 15")
    private String userAccountName;

    @NotEmpty
    private String city;

    @NotEmpty
    private String region;

    @NotEmpty(message = "To pole nie może byc puste")
    @Email
    private String loginByEmail;

    @NotEmpty(message = "To pole nie może byc puste")
    private String password;

    @NotEmpty(message = "To pole nie może byc puste")
    private String street;

    @NotEmpty(message = "To pole nie może byc puste")
    private String number;

    @NotEmpty(message = "To pole nie może byc puste")
    private String cityCode;

    private LocalDateTime accountCreationDate;

}
