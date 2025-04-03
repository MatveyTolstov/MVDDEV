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
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_department") // Изменено на snake_case
    private long idDepartment;

    @Column(name = "name") // Изменено на snake_case
    private String name;

    @Column(name = "address") // Изменено на snake_case
    private String address;
}