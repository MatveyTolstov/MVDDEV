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
    private long idApplication;

    private String nameApplication;

    private String description;

    private String status;

    private Date submissionDate;

    private Date completionDate;

    @ManyToOne
    @JoinColumn(name = "citizenId", referencedColumnName = "idCitizen")
    private Citizen citizen;


    @ManyToOne
    @JoinColumn(name = "idDocumentType", referencedColumnName = "documentTypeId")
    private DocumentType documentType;


}