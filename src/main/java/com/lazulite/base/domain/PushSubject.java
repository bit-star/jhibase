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
 * A PushSubject.
 */
@Entity
@Table(name = "push_subject")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PushSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "name")
    private String name;

    @Column(name = "agent_group_id")
    private String agentGroupId;

    @Column(name = "title_color")
    private String titleColor;

    @Column(name = "remark")
    private String remark;

    @OneToMany(mappedBy = "pushSubject")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProcessMsgTask> processMsgTasks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("pushSubjects")
    private FmpSubCompany fmpSubCompany;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgentId() {
        return agentId;
    }

    public PushSubject agentId(Long agentId) {
        this.agentId = agentId;
        return this;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public PushSubject name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgentGroupId() {
        return agentGroupId;
    }

    public PushSubject agentGroupId(String agentGroupId) {
        this.agentGroupId = agentGroupId;
        return this;
    }

    public void setAgentGroupId(String agentGroupId) {
        this.agentGroupId = agentGroupId;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public PushSubject titleColor(String titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getRemark() {
        return remark;
    }

    public PushSubject remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<ProcessMsgTask> getProcessMsgTasks() {
        return processMsgTasks;
    }

    public PushSubject processMsgTasks(Set<ProcessMsgTask> processMsgTasks) {
        this.processMsgTasks = processMsgTasks;
        return this;
    }

    public PushSubject addProcessMsgTask(ProcessMsgTask processMsgTask) {
        this.processMsgTasks.add(processMsgTask);
        processMsgTask.setPushSubject(this);
        return this;
    }

    public PushSubject removeProcessMsgTask(ProcessMsgTask processMsgTask) {
        this.processMsgTasks.remove(processMsgTask);
        processMsgTask.setPushSubject(null);
        return this;
    }

    public void setProcessMsgTasks(Set<ProcessMsgTask> processMsgTasks) {
        this.processMsgTasks = processMsgTasks;
    }

    public FmpSubCompany getFmpSubCompany() {
        return fmpSubCompany;
    }

    public PushSubject fmpSubCompany(FmpSubCompany fmpSubCompany) {
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
        if (!(o instanceof PushSubject)) {
            return false;
        }
        return id != null && id.equals(((PushSubject) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PushSubject{" +
            "id=" + getId() +
            ", agentId=" + getAgentId() +
            ", name='" + getName() + "'" +
            ", agentGroupId='" + getAgentGroupId() + "'" +
            ", titleColor='" + getTitleColor() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
