package com.example.mvd_dev.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private long idUser;

    private String number;

    private String password;

    @ManyToOne
    @JoinColumn(name = "roleID", referencedColumnName = "idRole")
    private Role role;

    public long getRoleId(){
        return role != null ? role.getIdRole() : -1;
    }

}
