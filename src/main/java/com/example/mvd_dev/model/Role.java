package com.example.mvd_dev.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private long idRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private Roles roleName;

    public Role(Roles roleName) {
        this.roleName = roleName;
    }

    //тут костыль с ролью, почему то в других местах нельзя вызвать name()
    public String getRoleNameString() {  // Возвращаемый тип — Roles, а не Enum
        return this.roleName.name();
    }

}

