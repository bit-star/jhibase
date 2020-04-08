package com.lazulite.base.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
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

    @OneToMany(mappedBy = "fmpSubCompany")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MsgReceiverGroup> msgReceiverGroups = new HashSet<>();

    @OneToMany(mappedBy = "fmpSubCompany")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PushSubject> pushSubjects = new HashSet<>();

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

    public FmpSubCompany msgReceiverGroups(Set<MsgReceiverGroup> msgReceiverGroups) {
        this.msgReceiverGroups = msgReceiverGroups;
        return this;
    }

    public FmpSubCompany addMsgReceiverGroup(MsgReceiverGroup msgReceiverGroup) {
        this.msgReceiverGroups.add(msgReceiverGroup);
        msgReceiverGroup.setFmpSubCompany(this);
        return this;
    }

    public FmpSubCompany removeMsgReceiverGroup(MsgReceiverGroup msgReceiverGroup) {
        this.msgReceiverGroups.remove(msgReceiverGroup);
        msgReceiverGroup.setFmpSubCompany(null);
        return this;
    }

    public void setMsgReceiverGroups(Set<MsgReceiverGroup> msgReceiverGroups) {
        this.msgReceiverGroups = msgReceiverGroups;
    }

    public Set<PushSubject> getPushSubjects() {
        return pushSubjects;
    }

    public FmpSubCompany pushSubjects(Set<PushSubject> pushSubjects) {
        this.pushSubjects = pushSubjects;
        return this;
    }

    public FmpSubCompany addPushSubject(PushSubject pushSubject) {
        this.pushSubjects.add(pushSubject);
        pushSubject.setFmpSubCompany(this);
        return this;
    }

    public FmpSubCompany removePushSubject(PushSubject pushSubject) {
        this.pushSubjects.remove(pushSubject);
        pushSubject.setFmpSubCompany(null);
        return this;
    }

    public void setPushSubjects(Set<PushSubject> pushSubjects) {
        this.pushSubjects = pushSubjects;
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
            "}";
    }
}
