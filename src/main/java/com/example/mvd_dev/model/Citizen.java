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
    private long idCitizen;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "idUser ")
    private User user;

    // Другие поля и методы, если необходимо
}