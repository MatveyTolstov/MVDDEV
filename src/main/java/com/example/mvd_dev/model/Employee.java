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
    @Column(name = "id_employee")
    private long idEmployee;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id_department", nullable = false)
    private Department department;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    private User user;


}