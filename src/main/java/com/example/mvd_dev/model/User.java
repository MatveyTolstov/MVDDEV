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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user") // Изменено на snake_case
    private long idUser ;

    @Column(name = "number") // Изменено на snake_case
    private String number;

    @Column(name = "password") // Изменено на snake_case
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id_role") // Изменено на snake_case
    private Role role;

    public long getRoleId() {
        return role != null ? role.getIdRole() : -1;
    }
}