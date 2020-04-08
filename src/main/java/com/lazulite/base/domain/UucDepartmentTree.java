package com.lazulite.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

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

    @ManyToOne
    @JsonIgnoreProperties("uucDepartmentTrees")
    private MsgReceiverGroup msgReceiverGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MsgReceiverGroup getMsgReceiverGroup() {
        return msgReceiverGroup;
    }

    public UucDepartmentTree msgReceiverGroup(MsgReceiverGroup msgReceiverGroup) {
        this.msgReceiverGroup = msgReceiverGroup;
        return this;
    }

    public void setMsgReceiverGroup(MsgReceiverGroup msgReceiverGroup) {
        this.msgReceiverGroup = msgReceiverGroup;
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
