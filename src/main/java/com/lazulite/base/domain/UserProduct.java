package com.lazulite.base.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A UserProduct.
 */
@Entity
@Table(name = "user_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_des")
    private String productDes;

    @Column(name = "img_url")
    private String imgURL;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "update_date")
    private Instant updateDate;

    @ManyToOne
    @JsonIgnoreProperties("userProducts")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public UserProduct productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDes() {
        return productDes;
    }

    public UserProduct productDes(String productDes) {
        this.productDes = productDes;
        return this;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public String getImgURL() {
        return imgURL;
    }

    public UserProduct imgURL(String imgURL) {
        this.imgURL = imgURL;
        return this;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public UserProduct createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public UserProduct updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public User getUser() {
        return user;
    }

    public UserProduct user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserProduct)) {
            return false;
        }
        return id != null && id.equals(((UserProduct) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserProduct{" +
            "id=" + getId() +
            ", productName='" + getProductName() + "'" +
            ", productDes='" + getProductDes() + "'" +
            ", imgURL='" + getImgURL() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            "}";
    }
}
