package com.lazulite.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A MsgReceiverGroup.
 */
@Entity
@Table(name = "msg_receiver_group")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MsgReceiverGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_desc")
    private String desc;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "msg_receiver_group_uuc_department_tree",
               joinColumns = @JoinColumn(name = "msg_receiver_group_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "uuc_department_tree_id", referencedColumnName = "id"))
    private Set<UucDepartmentTree> uucDepartmentTrees = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "msg_receiver_group_uuc_user_baseinfo",
               joinColumns = @JoinColumn(name = "msg_receiver_group_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "uuc_user_baseinfo_id", referencedColumnName = "id"))
    private Set<UucUserBaseinfo> uucUserBaseinfos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("msgReceiverGroups")
    private FmpSubCompany fmpSubCompany;

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

    public MsgReceiverGroup name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public MsgReceiverGroup desc(String desc) {
        this.desc = desc;
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Set<UucDepartmentTree> getUucDepartmentTrees() {
        return uucDepartmentTrees;
    }

    public MsgReceiverGroup uucDepartmentTrees(Set<UucDepartmentTree> uucDepartmentTrees) {
        this.uucDepartmentTrees = uucDepartmentTrees;
        return this;
    }

    public MsgReceiverGroup addUucDepartmentTree(UucDepartmentTree uucDepartmentTree) {
        this.uucDepartmentTrees.add(uucDepartmentTree);
        uucDepartmentTree.getMsgReceiverGroups().add(this);
        return this;
    }

    public MsgReceiverGroup removeUucDepartmentTree(UucDepartmentTree uucDepartmentTree) {
        this.uucDepartmentTrees.remove(uucDepartmentTree);
        uucDepartmentTree.getMsgReceiverGroups().remove(this);
        return this;
    }

    public void setUucDepartmentTrees(Set<UucDepartmentTree> uucDepartmentTrees) {
        this.uucDepartmentTrees = uucDepartmentTrees;
    }

    public Set<UucUserBaseinfo> getUucUserBaseinfos() {
        return uucUserBaseinfos;
    }

    public MsgReceiverGroup uucUserBaseinfos(Set<UucUserBaseinfo> uucUserBaseinfos) {
        this.uucUserBaseinfos = uucUserBaseinfos;
        return this;
    }

    public MsgReceiverGroup addUucUserBaseinfo(UucUserBaseinfo uucUserBaseinfo) {
        this.uucUserBaseinfos.add(uucUserBaseinfo);
        uucUserBaseinfo.getMsgReceiverGroups().add(this);
        return this;
    }

    public MsgReceiverGroup removeUucUserBaseinfo(UucUserBaseinfo uucUserBaseinfo) {
        this.uucUserBaseinfos.remove(uucUserBaseinfo);
        uucUserBaseinfo.getMsgReceiverGroups().remove(this);
        return this;
    }

    public void setUucUserBaseinfos(Set<UucUserBaseinfo> uucUserBaseinfos) {
        this.uucUserBaseinfos = uucUserBaseinfos;
    }

    public FmpSubCompany getFmpSubCompany() {
        return fmpSubCompany;
    }

    public MsgReceiverGroup fmpSubCompany(FmpSubCompany fmpSubCompany) {
        this.fmpSubCompany = fmpSubCompany;
        return this;
    }

    public void setFmpSubCompany(FmpSubCompany fmpSubCompany) {
        this.fmpSubCompany = fmpSubCompany;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MsgReceiverGroup)) {
            return false;
        }
        return id != null && id.equals(((MsgReceiverGroup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MsgReceiverGroup{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
