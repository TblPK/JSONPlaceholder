package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Entity class representing a user role.
 */
@Entity
@Table(name = "role")
@Data
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    /**
     * The name of the role.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String name;

    /**
     * Retrieves the authority of the role.
     *
     * @return The name of the role.
     */
    @Override
    @JsonIgnore
    public String getAuthority() {
        return getName();
    }
}
