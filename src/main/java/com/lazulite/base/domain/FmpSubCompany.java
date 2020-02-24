package com.lazulite.base.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A FmpSubCompany.
 */
@Entity
@Table(name = "fmp_sub_company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FmpSubCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "admin_group_id")
    private String adminGroupId;

    @Column(name = "if_public")
    private String ifPublic;

    @Column(name = "style_id")
    private String styleId;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "fmpSubCompany")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ServiceWindow> serviceWindows = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public FmpSubCompany name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public FmpSubCompany code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAdminGroupId() {
        return adminGroupId;
    }

    public FmpSubCompany adminGroupId(String adminGroupId) {
        this.adminGroupId = adminGroupId;
        return this;
    }

    public void setAdminGroupId(String adminGroupId) {
        this.adminGroupId = adminGroupId;
    }

    public String getIfPublic() {
        return ifPublic;
    }

    public FmpSubCompany ifPublic(String ifPublic) {
        this.ifPublic = ifPublic;
        return this;
    }

    public void setIfPublic(String ifPublic) {
        this.ifPublic = ifPublic;
    }

    public String getStyleId() {
        return styleId;
    }

    public FmpSubCompany styleId(String styleId) {
        this.styleId = styleId;
        return this;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public Boolean isIsDeleted() {
        return isDeleted;
    }

    public FmpSubCompany isDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Set<ServiceWindow> getServiceWindows() {
        return serviceWindows;
    }

    public FmpSubCompany serviceWindows(Set<ServiceWindow> serviceWindows) {
        this.serviceWindows = serviceWindows;
        return this;
    }

    public FmpSubCompany addServiceWindow(ServiceWindow serviceWindow) {
        this.serviceWindows.add(serviceWindow);
        serviceWindow.setFmpSubCompany(this);
        return this;
    }

    public FmpSubCompany removeServiceWindow(ServiceWindow serviceWindow) {
        this.serviceWindows.remove(serviceWindow);
        serviceWindow.setFmpSubCompany(null);
        return this;
    }

    public void setServiceWindows(Set<ServiceWindow> serviceWindows) {
        this.serviceWindows = serviceWindows;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FmpSubCompany)) {
            return false;
        }
        return id != null && id.equals(((FmpSubCompany) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FmpSubCompany{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", adminGroupId='" + getAdminGroupId() + "'" +
            ", ifPublic='" + getIfPublic() + "'" +
            ", styleId='" + getStyleId() + "'" +
            ", isDeleted='" + isIsDeleted() + "'" +
            "}";
    }
}
