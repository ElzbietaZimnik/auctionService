package com.aukcje.user;

import com.aukcje.address.Address;
import com.aukcje.model.AccountStatus;
import com.aukcje.model.AccountType;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@Entity
@Table
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @NotNull
    private Long id;

    @Column(unique=true)
    @NotEmpty
    public String userAccountName;

    @Column
    @NotEmpty
    private String city;

    @Column
    @NotEmpty
    private String region;

    @Column(unique=true)
    @NotEmpty
    @NotNull
    private String loginByEmail;

    @Column
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    //private LocalDateTime dateCreateAccount;

    @Override
    public String toString() {
        return "User [id=" + id
                + ", userAccountName=" + userAccountName
                + ", city=" + city
                + ", region=" + region
                + ", email=" + loginByEmail
                + ", password=" + password
                + ", street=" + address.getStreet()
                + ", number=" + address.getNumber()
                + ", cityCode=" + address.getCityCode()
                +"]";
    }

}
