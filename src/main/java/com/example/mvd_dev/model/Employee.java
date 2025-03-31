package com.example.mvd_dev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "employees")
public class Employee extends UserFIO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEmployee;

    @ManyToOne
    @JoinColumn(name = "departmentId", referencedColumnName = "idDepartment", nullable = false)
    private Department department;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "idUser ", nullable = false)
    private User user;

    // Вы можете добавить дополнительные поля и методы, если необходимо
}