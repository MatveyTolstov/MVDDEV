package com.example.mvd_dev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;
import java.sql.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_application")
    private long idApplication;

    @Column(name = "name_application")
    private String nameApplication;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "submission_date")
    private Date submissionDate;

    @Column(name = "completion_date")
    private Date completionDate;

    @ManyToOne
    @JoinColumn(name = "citizen_id", referencedColumnName = "id_citizen")
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "id_document_type", referencedColumnName = "document_type_id")
    private DocumentType documentType;

    public void setCitizenId(@Positive(message = "Citizen ID must be positive") long citizenId) {
        this.citizen = new Citizen();
        this.citizen.setIdCitizen(citizenId);
    }

    public @Positive(message = "Citizen ID must be positive") long getCitizenId() {
        return citizen != null ? citizen.getIdCitizen() : 0;
    }

    public void setDocumentTypeId(long documentTypeId) {
        this.documentType = new DocumentType();
        this.documentType.setDocumentTypeId(documentTypeId);
    }

    public @Positive(message = "Document Type ID must be positive") long getDocumentTypeId() {
        return documentType != null ? documentType.getDocumentTypeId() : 0;
    }
}