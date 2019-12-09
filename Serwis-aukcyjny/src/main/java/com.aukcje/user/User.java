package com.aukcje.user;

import com.aukcje.address.Address;
import com.aukcje.model.AccountStatus;
import com.aukcje.model.AccountType;
import com.aukcje.role.Role;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(unique = true)
    public String userAccountName;
    @Column
    private String city;
    @Column
    private String region;
    @Column(unique = true)
    @NotNull
    private String loginByEmail;
    @Column
    private String password;
    @Column
    private LocalDateTime accountCreationDate;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})  //ładowanie zachłanne
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User [id=" + id
                + ", userAccountName=" + userAccountName
                + ", city=" + city
                + ", region=" + region
                + ", email=" + loginByEmail
                + ", password=" + "***************"
                + ", street=" + address.getStreet()
                + ", number=" + address.getNumber()
                + ", cityCode=" + address.getCityCode()
                + "]";
    }
}
