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
 * A UucUserBaseinfo.
 */
@Entity
@Table(name = "uuc_user_baseinfo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UucUserBaseinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "uucUserBaseinfos")
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

    public UucUserBaseinfo msgReceiverGroups(Set<MsgReceiverGroup> msgReceiverGroups) {
        this.msgReceiverGroups = msgReceiverGroups;
        return this;
    }

    public UucUserBaseinfo addMsgReceiverGroup(MsgReceiverGroup msgReceiverGroup) {
        this.msgReceiverGroups.add(msgReceiverGroup);
        msgReceiverGroup.getUucUserBaseinfos().add(this);
        return this;
    }

    public UucUserBaseinfo removeMsgReceiverGroup(MsgReceiverGroup msgReceiverGroup) {
        this.msgReceiverGroups.remove(msgReceiverGroup);
        msgReceiverGroup.getUucUserBaseinfos().remove(this);
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
        if (!(o instanceof UucUserBaseinfo)) {
            return false;
        }
        return id != null && id.equals(((UucUserBaseinfo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UucUserBaseinfo{" +
            "id=" + getId() +
            "}";
    }
}
