package com.lazulite.base.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A HistorySearch.
 */
@Entity
@Table(name = "history_search")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HistorySearch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "search_conetnt")
    private String searchConetnt;

    @Column(name = "search_count")
    private Integer searchCount;

    @Column(name = "is_hot")
    private Integer isHot;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "update_date")
    private Instant updateDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchConetnt() {
        return searchConetnt;
    }

    public HistorySearch searchConetnt(String searchConetnt) {
        this.searchConetnt = searchConetnt;
        return this;
    }

    public void setSearchConetnt(String searchConetnt) {
        this.searchConetnt = searchConetnt;
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public HistorySearch searchCount(Integer searchCount) {
        this.searchCount = searchCount;
        return this;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public HistorySearch isHot(Integer isHot) {
        this.isHot = isHot;
        return this;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public HistorySearch createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public HistorySearch updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistorySearch)) {
            return false;
        }
        return id != null && id.equals(((HistorySearch) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "HistorySearch{" +
            "id=" + getId() +
            ", searchConetnt='" + getSearchConetnt() + "'" +
            ", searchCount=" + getSearchCount() +
            ", isHot=" + getIsHot() +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
