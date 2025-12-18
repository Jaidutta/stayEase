package com.learntocode.projects.stayEase.entity;

import com.learntocode.projects.stayEase.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {  // postgres internally uses user table, so creating User table like this will result in an error
    // hence app_user is defined
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // pg will automatically create index for a unique columns
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password; // will encode and then store the encoded psword in the db

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    // This will create another table for us which is going to be app_user_roles
    /*
        Table Content: This table will have two columns:
        user_id (Foreign Key): Links back to the User entity.
        roles (Value Column): Stores the actual role string (e.g., 'ADMIN').
     */

}
