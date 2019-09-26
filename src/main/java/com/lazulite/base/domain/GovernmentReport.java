package com.lazulite.base.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.lazulite.base.domain.enumeration.GovernmentReportType;

/**
 * A GovernmentReport.
 */
@Entity
@Table(name = "government_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GovernmentReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private GovernmentReportType type;

    @Column(name = "report_date")
    private Instant reportDate;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "position")
    private String position;

    @Column(name = "contact_information")
    private String contactInformation;

    @Column(name = "content")
    private String content;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "governmentReport")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CspaceFile> cspaceFiles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GovernmentReportType getType() {
        return type;
    }

    public GovernmentReport type(GovernmentReportType type) {
        this.type = type;
        return this;
    }

    public void setType(GovernmentReportType type) {
        this.type = type;
    }

    public Instant getReportDate() {
        return reportDate;
    }

    public GovernmentReport reportDate(Instant reportDate) {
        this.reportDate = reportDate;
        return this;
    }

    public void setReportDate(Instant reportDate) {
        this.reportDate = reportDate;
    }

    public String getObjectName() {
        return objectName;
    }

    public GovernmentReport objectName(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getPosition() {
        return position;
    }

    public GovernmentReport position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public GovernmentReport contactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
        return this;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getContent() {
        return content;
    }

    public GovernmentReport content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public GovernmentReport location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<CspaceFile> getCspaceFiles() {
        return cspaceFiles;
    }

    public GovernmentReport cspaceFiles(Set<CspaceFile> cspaceFiles) {
        this.cspaceFiles = cspaceFiles;
        return this;
    }

    public GovernmentReport addCspaceFile(CspaceFile cspaceFile) {
        this.cspaceFiles.add(cspaceFile);
        cspaceFile.setGovernmentReport(this);
        return this;
    }

    public GovernmentReport removeCspaceFile(CspaceFile cspaceFile) {
        this.cspaceFiles.remove(cspaceFile);
        cspaceFile.setGovernmentReport(null);
        return this;
    }

    public void setCspaceFiles(Set<CspaceFile> cspaceFiles) {
        this.cspaceFiles = cspaceFiles;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GovernmentReport)) {
            return false;
        }
        return id != null && id.equals(((GovernmentReport) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GovernmentReport{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", reportDate='" + getReportDate() + "'" +
            ", objectName='" + getObjectName() + "'" +
            ", position='" + getPosition() + "'" +
            ", contactInformation='" + getContactInformation() + "'" +
            ", content='" + getContent() + "'" +
            ", location='" + getLocation() + "'" +
            "}";
    }
}
