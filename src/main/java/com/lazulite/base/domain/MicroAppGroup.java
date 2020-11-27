package com.lazulite.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A MicroAppGroup.
 */
@Entity
@Table(name = "micro_app_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MicroAppGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "group")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<UucDepartmentTree> uucDepartmentTrees = new HashSet<>();

    @OneToMany(mappedBy = "group")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<UucUserBaseinfo> uucUserBaseinfos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "micro_app_group_fmp_micro_app",
               joinColumns = @JoinColumn(name = "micro_app_group_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "fmp_micro_app_id", referencedColumnName = "id"))
    private Set<FmpMicroApp> fmpMicroApps = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "microAppGroups", allowSetters = true)
    private FmpSubCompany fmpSubCompany;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public MicroAppGroup name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UucDepartmentTree> getUucDepartmentTrees() {
        return uucDepartmentTrees;
    }

    public MicroAppGroup uucDepartmentTrees(Set<UucDepartmentTree> uucDepartmentTrees) {
        this.uucDepartmentTrees = uucDepartmentTrees;
        return this;
    }

    public MicroAppGroup addUucDepartmentTree(UucDepartmentTree uucDepartmentTree) {
        this.uucDepartmentTrees.add(uucDepartmentTree);
        uucDepartmentTree.setGroup(this);
        return this;
    }

    public MicroAppGroup removeUucDepartmentTree(UucDepartmentTree uucDepartmentTree) {
        this.uucDepartmentTrees.remove(uucDepartmentTree);
        uucDepartmentTree.setGroup(null);
        return this;
    }

    public void setUucDepartmentTrees(Set<UucDepartmentTree> uucDepartmentTrees) {
        this.uucDepartmentTrees = uucDepartmentTrees;
    }

    public Set<UucUserBaseinfo> getUucUserBaseinfos() {
        return uucUserBaseinfos;
    }

    public MicroAppGroup uucUserBaseinfos(Set<UucUserBaseinfo> uucUserBaseinfos) {
        this.uucUserBaseinfos = uucUserBaseinfos;
        return this;
    }

    public MicroAppGroup addUucUserBaseinfo(UucUserBaseinfo uucUserBaseinfo) {
        this.uucUserBaseinfos.add(uucUserBaseinfo);
        uucUserBaseinfo.setGroup(this);
        return this;
    }

    public MicroAppGroup removeUucUserBaseinfo(UucUserBaseinfo uucUserBaseinfo) {
        this.uucUserBaseinfos.remove(uucUserBaseinfo);
        uucUserBaseinfo.setGroup(null);
        return this;
    }

    public void setUucUserBaseinfos(Set<UucUserBaseinfo> uucUserBaseinfos) {
        this.uucUserBaseinfos = uucUserBaseinfos;
    }

    public Set<FmpMicroApp> getFmpMicroApps() {
        return fmpMicroApps;
    }

    public MicroAppGroup fmpMicroApps(Set<FmpMicroApp> fmpMicroApps) {
        this.fmpMicroApps = fmpMicroApps;
        return this;
    }

    public MicroAppGroup addFmpMicroApp(FmpMicroApp fmpMicroApp) {
        this.fmpMicroApps.add(fmpMicroApp);
        fmpMicroApp.getMicroAppGroups().add(this);
        return this;
    }

    public MicroAppGroup removeFmpMicroApp(FmpMicroApp fmpMicroApp) {
        this.fmpMicroApps.remove(fmpMicroApp);
        fmpMicroApp.getMicroAppGroups().remove(this);
        return this;
    }

    public void setFmpMicroApps(Set<FmpMicroApp> fmpMicroApps) {
        this.fmpMicroApps = fmpMicroApps;
    }

    public FmpSubCompany getFmpSubCompany() {
        return fmpSubCompany;
    }

    public MicroAppGroup fmpSubCompany(FmpSubCompany fmpSubCompany) {
        this.fmpSubCompany = fmpSubCompany;
        return this;
    }

    public void setFmpSubCompany(FmpSubCompany fmpSubCompany) {
        this.fmpSubCompany = fmpSubCompany;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MicroAppGroup)) {
            return false;
        }
        return id != null && id.equals(((MicroAppGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MicroAppGroup{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
