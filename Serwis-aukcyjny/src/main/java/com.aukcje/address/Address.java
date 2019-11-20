package com.aukcje.address;

import com.aukcje.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    @NotNull
    private Long id;

    @Column
    @NotEmpty
    private String street;

    @Column
    @NotEmpty
    private Long number;

    @Column
    @NotEmpty
    private Integer cityCode;

    @OneToMany(mappedBy = "address")
    private List<User> users;

    @Override
    public String toString() {
        return "User [id=" + id
                + ", street=" + street
                + ", number=" + number
                + ", cityCode=" + cityCode
                +"]";
    }
}
