package com.lazulite.base.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A CspaceFile.
 */
@Entity
@Table(name = "cspace_file")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CspaceFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "space_id")
    private String spaceId;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_type")
    private String fileType;

    @ManyToOne
    @JsonIgnoreProperties("cspaceFiles")
    private GovernmentReport governmentReport;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public CspaceFile spaceId(String spaceId) {
        this.spaceId = spaceId;
        return this;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public String getFileId() {
        return fileId;
    }

    public CspaceFile fileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public CspaceFile fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public CspaceFile fileSize(Long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public CspaceFile fileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public GovernmentReport getGovernmentReport() {
        return governmentReport;
    }

    public CspaceFile governmentReport(GovernmentReport governmentReport) {
        this.governmentReport = governmentReport;
        return this;
    }

    public void setGovernmentReport(GovernmentReport governmentReport) {
        this.governmentReport = governmentReport;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CspaceFile)) {
            return false;
        }
        return id != null && id.equals(((CspaceFile) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CspaceFile{" +
            "id=" + getId() +
            ", spaceId='" + getSpaceId() + "'" +
            ", fileId='" + getFileId() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", fileSize=" + getFileSize() +
            ", fileType='" + getFileType() + "'" +
            "}";
    }
}
