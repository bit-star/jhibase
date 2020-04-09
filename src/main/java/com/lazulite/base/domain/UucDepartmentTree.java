package com.lazulite.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A UucDepartmentTree.
 */
@Entity
@Table(name = "uuc_department_tree")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UucDepartmentTree implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "uucDepartmentTrees")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<MsgReceiverGroup> msgReceiverGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MsgReceiverGroup> getMsgReceiverGroups() {
        return msgReceiverGroups;
    }

    public UucDepartmentTree msgReceiverGroups(Set<MsgReceiverGroup> msgReceiverGroups) {
        this.msgReceiverGroups = msgReceiverGroups;
        return this;
    }

    public UucDepartmentTree addMsgReceiverGroup(MsgReceiverGroup msgReceiverGroup) {
        this.msgReceiverGroups.add(msgReceiverGroup);
        msgReceiverGroup.getUucDepartmentTrees().add(this);
        return this;
    }

    public UucDepartmentTree removeMsgReceiverGroup(MsgReceiverGroup msgReceiverGroup) {
        this.msgReceiverGroups.remove(msgReceiverGroup);
        msgReceiverGroup.getUucDepartmentTrees().remove(this);
        return this;
    }

    public void setMsgReceiverGroups(Set<MsgReceiverGroup> msgReceiverGroups) {
        this.msgReceiverGroups = msgReceiverGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UucDepartmentTree)) {
            return false;
        }
        return id != null && id.equals(((UucDepartmentTree) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UucDepartmentTree{" +
            "id=" + getId() +
            "}";
    }
}
