package com.example.mvd_dev.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Role {
    @Id
    private long idRole;

    public String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

}
