package com.example.mvd_dev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_application") // Изменено на snake_case
    private long idApplication;

    @Column(name = "name_application") // Изменено на snake_case
    private String nameApplication;

    @Column(name = "description") // Изменено на snake_case
    private String description;

    @Column(name = "status") // Изменено на snake_case
    private String status;

    @Column(name = "submission_date") // Изменено на snake_case
    private Date submissionDate;

    @Column(name = "completion_date") // Изменено на snake_case
    private Date completionDate;

    @ManyToOne
    @JoinColumn(name = "citizen_id", referencedColumnName = "id_citizen") // Изменено на snake_case
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "id_document_type", referencedColumnName = "document_type_id") // Изменено на snake_case
    private DocumentType documentType;
}