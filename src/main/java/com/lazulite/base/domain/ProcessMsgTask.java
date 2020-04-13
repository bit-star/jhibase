package com.lazulite.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ProcessMsgTask.
 */
@Entity
@Table(name = "process_msg_task")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ProcessMsgTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("processMsgTasks")
    private PushSubject pushSubject;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PushSubject getPushSubject() {
        return pushSubject;
    }

    public ProcessMsgTask pushSubject(PushSubject pushSubject) {
        this.pushSubject = pushSubject;
        return this;
    }

    public void setPushSubject(PushSubject pushSubject) {
        this.pushSubject = pushSubject;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProcessMsgTask)) {
            return false;
        }
        return id != null && id.equals(((ProcessMsgTask) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ProcessMsgTask{" +
            "id=" + getId() +
            "}";
    }
}
