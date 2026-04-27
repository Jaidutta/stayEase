package com.learntocode.projects.stayEase.entity;

import com.learntocode.projects.stayEase.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User implements UserDetails {  // postgres internally uses user table, so creating User table like this will result in an error
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


    // This will create another table for us which is going to be app_user_roles
    /*
        Table Content: This table will have two columns:
        user_id (Foreign Key): Links back to the User entity.
        roles (Value Column): Stores the actual role string (e.g., 'ADMIN').
     */

}
