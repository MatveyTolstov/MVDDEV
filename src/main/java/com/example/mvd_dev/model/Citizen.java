package com.example.mvd_dev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "citizens")
public class Citizen extends UserFIO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_citizen") // Изменено на snake_case
    private long idCitizen;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user") // Изменено на snake_case
    private User user;

    // Другие поля и методы, если необходимо
}