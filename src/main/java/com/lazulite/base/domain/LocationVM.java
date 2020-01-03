package com.lazulite.base.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.lazulite.base.domain.enumeration.WorkLogType;

/**
 * A LocationVM.
 */
@Entity
@Table(name = "location_vm")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class LocationVM implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "station_id")
    private Long stationId;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "station_name")
    private String stationName;

    @Column(name = "longitude", precision = 21, scale = 2)
    private BigDecimal longitude;

    @Column(name = "latitude", precision = 21, scale = 2)
    private BigDecimal latitude;

    @Column(name = "accuracy")
    private String accuracy;

    @Column(name = "address")
    private String address;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "road")
    private String road;

    @Column(name = "net_type")
    private String netType;

    @Column(name = "operator_type")
    private String operatorType;

    @Column(name = "dd_user_name")
    private String ddUserName;

    @Column(name = "dd_user_id")
    private Long ddUserId;

    @Column(name = "dd_user_phone")
    private String ddUserPhone;

    @Column(name = "is_finish")
    private Boolean isFinish;

    @Column(name = "work_log_main_id")
    private Long workLogMainId;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_log_type")
    private WorkLogType workLogType;

    @Column(name = "created_date")
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStationId() {
        return stationId;
    }

    public LocationVM stationId(Long stationId) {
        this.stationId = stationId;
        return this;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getGroupName() {
        return groupName;
    }

    public LocationVM groupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStationName() {
        return stationName;
    }

    public LocationVM stationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public LocationVM longitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public LocationVM latitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public LocationVM accuracy(String accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getAddress() {
        return address;
    }

    public LocationVM address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public LocationVM province(String province) {
        this.province = province;
        return this;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public LocationVM city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public LocationVM district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRoad() {
        return road;
    }

    public LocationVM road(String road) {
        this.road = road;
        return this;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getNetType() {
        return netType;
    }

    public LocationVM netType(String netType) {
        this.netType = netType;
        return this;
    }

    public void setNetType(String netType) {
        this.netType = netType;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public LocationVM operatorType(String operatorType) {
        this.operatorType = operatorType;
        return this;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public String getDdUserName() {
        return ddUserName;
    }

    public LocationVM ddUserName(String ddUserName) {
        this.ddUserName = ddUserName;
        return this;
    }

    public void setDdUserName(String ddUserName) {
        this.ddUserName = ddUserName;
    }

    public Long getDdUserId() {
        return ddUserId;
    }

    public LocationVM ddUserId(Long ddUserId) {
        this.ddUserId = ddUserId;
        return this;
    }

    public void setDdUserId(Long ddUserId) {
        this.ddUserId = ddUserId;
    }

    public String getDdUserPhone() {
        return ddUserPhone;
    }

    public LocationVM ddUserPhone(String ddUserPhone) {
        this.ddUserPhone = ddUserPhone;
        return this;
    }

    public void setDdUserPhone(String ddUserPhone) {
        this.ddUserPhone = ddUserPhone;
    }

    public Boolean isIsFinish() {
        return isFinish;
    }

    public LocationVM isFinish(Boolean isFinish) {
        this.isFinish = isFinish;
        return this;
    }

    public void setIsFinish(Boolean isFinish) {
        this.isFinish = isFinish;
    }

    public Long getWorkLogMainId() {
        return workLogMainId;
    }

    public LocationVM workLogMainId(Long workLogMainId) {
        this.workLogMainId = workLogMainId;
        return this;
    }

    public void setWorkLogMainId(Long workLogMainId) {
        this.workLogMainId = workLogMainId;
    }

    public WorkLogType getWorkLogType() {
        return workLogType;
    }

    public LocationVM workLogType(WorkLogType workLogType) {
        this.workLogType = workLogType;
        return this;
    }

    public void setWorkLogType(WorkLogType workLogType) {
        this.workLogType = workLogType;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public LocationVM createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocationVM)) {
            return false;
        }
        return id != null && id.equals(((LocationVM) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LocationVM{" +
            "id=" + getId() +
            ", stationId=" + getStationId() +
            ", groupName='" + getGroupName() + "'" +
            ", stationName='" + getStationName() + "'" +
            ", longitude=" + getLongitude() +
            ", latitude=" + getLatitude() +
            ", accuracy='" + getAccuracy() + "'" +
            ", address='" + getAddress() + "'" +
            ", province='" + getProvince() + "'" +
            ", city='" + getCity() + "'" +
            ", district='" + getDistrict() + "'" +
            ", road='" + getRoad() + "'" +
            ", netType='" + getNetType() + "'" +
            ", operatorType='" + getOperatorType() + "'" +
            ", ddUserName='" + getDdUserName() + "'" +
            ", ddUserId=" + getDdUserId() +
            ", ddUserPhone='" + getDdUserPhone() + "'" +
            ", isFinish='" + isIsFinish() + "'" +
            ", workLogMainId=" + getWorkLogMainId() +
            ", workLogType='" + getWorkLogType() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}
