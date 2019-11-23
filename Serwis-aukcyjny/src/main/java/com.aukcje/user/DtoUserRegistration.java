package com.aukcje.user;

import com.aukcje.address.Address;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
public class DtoUserRegistration {
    @NotEmpty (message = "To pole nie może byc puste")
    @Size(min = 3)
    private String userAccountName;

    @NotEmpty
    private String city;

    @NotEmpty
    private String region;

    @NotEmpty(message = "To pole nie może byc puste")
    @Email
    private String loginByEmail;

    @NotEmpty
    private String password;

    @NotEmpty
    private String street;

    @NotEmpty
    private String number;


    private Long cityCode;

}
