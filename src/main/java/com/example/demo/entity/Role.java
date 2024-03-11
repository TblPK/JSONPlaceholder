package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role")
@Data
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String name;

    @Override
    @JsonIgnore
    public String getAuthority() {
        return getName();
    }
}