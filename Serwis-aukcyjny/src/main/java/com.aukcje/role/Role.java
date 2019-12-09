package com.aukcje.role;

import com.aukcje.user.User;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String role;
    @Column
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}