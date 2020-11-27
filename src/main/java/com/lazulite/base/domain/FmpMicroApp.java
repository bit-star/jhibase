package com.lazulite.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.lazulite.base.domain.enumeration.IsNew;

import com.lazulite.base.domain.enumeration.MicroAppStatus;

import com.lazulite.base.domain.enumeration.OpenMethod;

import com.lazulite.base.domain.enumeration.Language;

import com.lazulite.base.domain.enumeration.IsFixed;

/**
 * A FmpMicroApp.
 */
@Entity
@Table(name = "fmp_micro_app")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FmpMicroApp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cat_code")
    private String catCode;

    @Column(name = "parent_cat_id")
    private String parentCatId;

    @Column(name = "level_number")
    private Integer levelNumber;

    @Column(name = "is_leaf")
    private String isLeaf;

    @Column(name = "business_code")
    private String businessCode;

    @Column(name = "endpoint_url")
    private String endpointUrl;

    @Column(name = "icon_img")
    private String iconImg;

    @Column(name = "banner_img")
    private String bannerImg;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "sort_code")
    private String sortCode;

    @Column(name = "system_type")
    private String systemType;

    @Column(name = "content_owner_code")
    private String contentOwnerCode;

    @Column(name = "lable")
    private String lable;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_new")
    private IsNew isNew;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private MicroAppStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "open_method")
    private OpenMethod openMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_fixed")
    private IsFixed isFixed;

    @Column(name = "tag_key_01")
    private String tagKey01;

    @Column(name = "tag_val_01")
    private String tagVal01;

    @Column(name = "tag_key_02")
    private String tagKey02;

    @Column(name = "tag_val_02")
    private String tagVal02;

    @Column(name = "tag_key_03")
    private String tagKey03;

    @Column(name = "tag_val_03")
    private String tagVal03;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "filter_sql")
    private String filterSql;

    @Column(name = "sharing_flag")
    private String sharingFlag;

    @Column(name = "category")
    private String category;

    @Column(name = "img_class")
    private String imgClass;

    @Column(name = "is_internal")
    private String isInternal;

    @Column(name = "custom_flag")
    private String customFlag;

    @Column(name = "portal")
    private String portal;

    @Column(name = "description")
    private String description;

    @Column(name = "business_unit")
    private String businessUnit;

    @Column(name = "business_owners")
    private String businessOwners;

    @Column(name = "business_owners_mobile")
    private String businessOwnersMobile;

    @OneToMany(mappedBy = "fmpMicroApp")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<FmpWidgetInfo> fmpWidgetInfos = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "fmp_micro_app_uuc_department_trees",
               joinColumns = @JoinColumn(name = "fmp_micro_app_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "uuc_department_trees_id", referencedColumnName = "id"))
    private Set<UucDepartmentTree> uucDepartmentTrees = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "fmp_micro_app_usable_user",
               joinColumns = @JoinColumn(name = "fmp_micro_app_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "usable_user_id", referencedColumnName = "id"))
    private Set<UucUserBaseinfo> usableUsers = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "fmpMicroApps", allowSetters = true)
    private FmpMicroAppType fmpMicroAppType;

    @ManyToOne
    @JsonIgnoreProperties(value = "createdApps", allowSetters = true)
    private FmpSubCompany creatorCompany;

    @ManyToMany(mappedBy = "fmpMicroApps")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<FmpSubCompany> fmpSubCompanies = new HashSet<>();

    @ManyToMany(mappedBy = "collectionFmpMicroApps")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<UucUserBaseinfo> collectionUsers = new HashSet<>();

    @ManyToMany(mappedBy = "fmpMicroApps")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<MicroAppGroup> microAppGroups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatCode() {
        return catCode;
    }

    public FmpMicroApp catCode(String catCode) {
        this.catCode = catCode;
        return this;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public String getParentCatId() {
        return parentCatId;
    }

    public FmpMicroApp parentCatId(String parentCatId) {
        this.parentCatId = parentCatId;
        return this;
    }

    public void setParentCatId(String parentCatId) {
        this.parentCatId = parentCatId;
    }

    public Integer getLevelNumber() {
        return levelNumber;
    }

    public FmpMicroApp levelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
        return this;
    }

    public void setLevelNumber(Integer levelNumber) {
        this.levelNumber = levelNumber;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public FmpMicroApp isLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
        return this;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public FmpMicroApp businessCode(String businessCode) {
        this.businessCode = businessCode;
        return this;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public FmpMicroApp endpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
        return this;
    }

    public void setEndpointUrl(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public String getIconImg() {
        return iconImg;
    }

    public FmpMicroApp iconImg(String iconImg) {
        this.iconImg = iconImg;
        return this;
    }

    public void setIconImg(String iconImg) {
        this.iconImg = iconImg;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public FmpMicroApp bannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
        return this;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public FmpMicroApp thumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSortCode() {
        return sortCode;
    }

    public FmpMicroApp sortCode(String sortCode) {
        this.sortCode = sortCode;
        return this;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getSystemType() {
        return systemType;
    }

    public FmpMicroApp systemType(String systemType) {
        this.systemType = systemType;
        return this;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getContentOwnerCode() {
        return contentOwnerCode;
    }

    public FmpMicroApp contentOwnerCode(String contentOwnerCode) {
        this.contentOwnerCode = contentOwnerCode;
        return this;
    }

    public void setContentOwnerCode(String contentOwnerCode) {
        this.contentOwnerCode = contentOwnerCode;
    }

    public String getLable() {
        return lable;
    }

    public FmpMicroApp lable(String lable) {
        this.lable = lable;
        return this;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public IsNew getIsNew() {
        return isNew;
    }

    public FmpMicroApp isNew(IsNew isNew) {
        this.isNew = isNew;
        return this;
    }

    public void setIsNew(IsNew isNew) {
        this.isNew = isNew;
    }

    public MicroAppStatus getStatus() {
        return status;
    }

    public FmpMicroApp status(MicroAppStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(MicroAppStatus status) {
        this.status = status;
    }

    public OpenMethod getOpenMethod() {
        return openMethod;
    }

    public FmpMicroApp openMethod(OpenMethod openMethod) {
        this.openMethod = openMethod;
        return this;
    }

    public void setOpenMethod(OpenMethod openMethod) {
        this.openMethod = openMethod;
    }

    public Language getLanguage() {
        return language;
    }

    public FmpMicroApp language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public IsFixed getIsFixed() {
        return isFixed;
    }

    public FmpMicroApp isFixed(IsFixed isFixed) {
        this.isFixed = isFixed;
        return this;
    }

    public void setIsFixed(IsFixed isFixed) {
        this.isFixed = isFixed;
    }

    public String getTagKey01() {
        return tagKey01;
    }

    public FmpMicroApp tagKey01(String tagKey01) {
        this.tagKey01 = tagKey01;
        return this;
    }

    public void setTagKey01(String tagKey01) {
        this.tagKey01 = tagKey01;
    }

    public String getTagVal01() {
        return tagVal01;
    }

    public FmpMicroApp tagVal01(String tagVal01) {
        this.tagVal01 = tagVal01;
        return this;
    }

    public void setTagVal01(String tagVal01) {
        this.tagVal01 = tagVal01;
    }

    public String getTagKey02() {
        return tagKey02;
    }

    public FmpMicroApp tagKey02(String tagKey02) {
        this.tagKey02 = tagKey02;
        return this;
    }

    public void setTagKey02(String tagKey02) {
        this.tagKey02 = tagKey02;
    }

    public String getTagVal02() {
        return tagVal02;
    }

    public FmpMicroApp tagVal02(String tagVal02) {
        this.tagVal02 = tagVal02;
        return this;
    }

    public void setTagVal02(String tagVal02) {
        this.tagVal02 = tagVal02;
    }

    public String getTagKey03() {
        return tagKey03;
    }

    public FmpMicroApp tagKey03(String tagKey03) {
        this.tagKey03 = tagKey03;
        return this;
    }

    public void setTagKey03(String tagKey03) {
        this.tagKey03 = tagKey03;
    }

    public String getTagVal03() {
        return tagVal03;
    }

    public FmpMicroApp tagVal03(String tagVal03) {
        this.tagVal03 = tagVal03;
        return this;
    }

    public void setTagVal03(String tagVal03) {
        this.tagVal03 = tagVal03;
    }

    public String getCatName() {
        return catName;
    }

    public FmpMicroApp catName(String catName) {
        this.catName = catName;
        return this;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getFilterSql() {
        return filterSql;
    }

    public FmpMicroApp filterSql(String filterSql) {
        this.filterSql = filterSql;
        return this;
    }

    public void setFilterSql(String filterSql) {
        this.filterSql = filterSql;
    }

    public String getSharingFlag() {
        return sharingFlag;
    }

    public FmpMicroApp sharingFlag(String sharingFlag) {
        this.sharingFlag = sharingFlag;
        return this;
    }

    public void setSharingFlag(String sharingFlag) {
        this.sharingFlag = sharingFlag;
    }

    public String getCategory() {
        return category;
    }

    public FmpMicroApp category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgClass() {
        return imgClass;
    }

    public FmpMicroApp imgClass(String imgClass) {
        this.imgClass = imgClass;
        return this;
    }

    public void setImgClass(String imgClass) {
        this.imgClass = imgClass;
    }

    public String getIsInternal() {
        return isInternal;
    }

    public FmpMicroApp isInternal(String isInternal) {
        this.isInternal = isInternal;
        return this;
    }

    public void setIsInternal(String isInternal) {
        this.isInternal = isInternal;
    }

    public String getCustomFlag() {
        return customFlag;
    }

    public FmpMicroApp customFlag(String customFlag) {
        this.customFlag = customFlag;
        return this;
    }

    public void setCustomFlag(String customFlag) {
        this.customFlag = customFlag;
    }

    public String getPortal() {
        return portal;
    }

    public FmpMicroApp portal(String portal) {
        this.portal = portal;
        return this;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getDescription() {
        return description;
    }

    public FmpMicroApp description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public FmpMicroApp businessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
        return this;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getBusinessOwners() {
        return businessOwners;
    }

    public FmpMicroApp businessOwners(String businessOwners) {
        this.businessOwners = businessOwners;
        return this;
    }

    public void setBusinessOwners(String businessOwners) {
        this.businessOwners = businessOwners;
    }

    public String getBusinessOwnersMobile() {
        return businessOwnersMobile;
    }

    public FmpMicroApp businessOwnersMobile(String businessOwnersMobile) {
        this.businessOwnersMobile = businessOwnersMobile;
        return this;
    }

    public void setBusinessOwnersMobile(String businessOwnersMobile) {
        this.businessOwnersMobile = businessOwnersMobile;
    }

    public Set<FmpWidgetInfo> getFmpWidgetInfos() {
        return fmpWidgetInfos;
    }

    public FmpMicroApp fmpWidgetInfos(Set<FmpWidgetInfo> fmpWidgetInfos) {
        this.fmpWidgetInfos = fmpWidgetInfos;
        return this;
    }

    public FmpMicroApp addFmpWidgetInfo(FmpWidgetInfo fmpWidgetInfo) {
        this.fmpWidgetInfos.add(fmpWidgetInfo);
        fmpWidgetInfo.setFmpMicroApp(this);
        return this;
    }

    public FmpMicroApp removeFmpWidgetInfo(FmpWidgetInfo fmpWidgetInfo) {
        this.fmpWidgetInfos.remove(fmpWidgetInfo);
        fmpWidgetInfo.setFmpMicroApp(null);
        return this;
    }

    public void setFmpWidgetInfos(Set<FmpWidgetInfo> fmpWidgetInfos) {
        this.fmpWidgetInfos = fmpWidgetInfos;
    }

    public Set<UucDepartmentTree> getUucDepartmentTrees() {
        return uucDepartmentTrees;
    }

    public FmpMicroApp uucDepartmentTrees(Set<UucDepartmentTree> uucDepartmentTrees) {
        this.uucDepartmentTrees = uucDepartmentTrees;
        return this;
    }

    public FmpMicroApp addUucDepartmentTrees(UucDepartmentTree uucDepartmentTree) {
        this.uucDepartmentTrees.add(uucDepartmentTree);
        uucDepartmentTree.getUsables().add(this);
        return this;
    }

    public FmpMicroApp removeUucDepartmentTrees(UucDepartmentTree uucDepartmentTree) {
        this.uucDepartmentTrees.remove(uucDepartmentTree);
        uucDepartmentTree.getUsables().remove(this);
        return this;
    }

    public void setUucDepartmentTrees(Set<UucDepartmentTree> uucDepartmentTrees) {
        this.uucDepartmentTrees = uucDepartmentTrees;
    }

    public Set<UucUserBaseinfo> getUsableUsers() {
        return usableUsers;
    }

    public FmpMicroApp usableUsers(Set<UucUserBaseinfo> uucUserBaseinfos) {
        this.usableUsers = uucUserBaseinfos;
        return this;
    }

    public FmpMicroApp addUsableUser(UucUserBaseinfo uucUserBaseinfo) {
        this.usableUsers.add(uucUserBaseinfo);
        uucUserBaseinfo.getUsableFmpMicroApps().add(this);
        return this;
    }

    public FmpMicroApp removeUsableUser(UucUserBaseinfo uucUserBaseinfo) {
        this.usableUsers.remove(uucUserBaseinfo);
        uucUserBaseinfo.getUsableFmpMicroApps().remove(this);
        return this;
    }

    public void setUsableUsers(Set<UucUserBaseinfo> uucUserBaseinfos) {
        this.usableUsers = uucUserBaseinfos;
    }

    public FmpMicroAppType getFmpMicroAppType() {
        return fmpMicroAppType;
    }

    public FmpMicroApp fmpMicroAppType(FmpMicroAppType fmpMicroAppType) {
        this.fmpMicroAppType = fmpMicroAppType;
        return this;
    }

    public void setFmpMicroAppType(FmpMicroAppType fmpMicroAppType) {
        this.fmpMicroAppType = fmpMicroAppType;
    }

    public FmpSubCompany getCreatorCompany() {
        return creatorCompany;
    }

    public FmpMicroApp creatorCompany(FmpSubCompany fmpSubCompany) {
        this.creatorCompany = fmpSubCompany;
        return this;
    }

    public void setCreatorCompany(FmpSubCompany fmpSubCompany) {
        this.creatorCompany = fmpSubCompany;
    }

    public Set<FmpSubCompany> getFmpSubCompanies() {
        return fmpSubCompanies;
    }

    public FmpMicroApp fmpSubCompanies(Set<FmpSubCompany> fmpSubCompanies) {
        this.fmpSubCompanies = fmpSubCompanies;
        return this;
    }

    public FmpMicroApp addFmpSubCompany(FmpSubCompany fmpSubCompany) {
        this.fmpSubCompanies.add(fmpSubCompany);
        fmpSubCompany.getFmpMicroApps().add(this);
        return this;
    }

    public FmpMicroApp removeFmpSubCompany(FmpSubCompany fmpSubCompany) {
        this.fmpSubCompanies.remove(fmpSubCompany);
        fmpSubCompany.getFmpMicroApps().remove(this);
        return this;
    }

    public void setFmpSubCompanies(Set<FmpSubCompany> fmpSubCompanies) {
        this.fmpSubCompanies = fmpSubCompanies;
    }

    public Set<UucUserBaseinfo> getCollectionUsers() {
        return collectionUsers;
    }

    public FmpMicroApp collectionUsers(Set<UucUserBaseinfo> uucUserBaseinfos) {
        this.collectionUsers = uucUserBaseinfos;
        return this;
    }

    public FmpMicroApp addCollectionUsers(UucUserBaseinfo uucUserBaseinfo) {
        this.collectionUsers.add(uucUserBaseinfo);
        uucUserBaseinfo.getCollectionFmpMicroApps().add(this);
        return this;
    }

    public FmpMicroApp removeCollectionUsers(UucUserBaseinfo uucUserBaseinfo) {
        this.collectionUsers.remove(uucUserBaseinfo);
        uucUserBaseinfo.getCollectionFmpMicroApps().remove(this);
        return this;
    }

    public void setCollectionUsers(Set<UucUserBaseinfo> uucUserBaseinfos) {
        this.collectionUsers = uucUserBaseinfos;
    }

    public Set<MicroAppGroup> getMicroAppGroups() {
        return microAppGroups;
    }

    public FmpMicroApp microAppGroups(Set<MicroAppGroup> microAppGroups) {
        this.microAppGroups = microAppGroups;
        return this;
    }

    public FmpMicroApp addMicroAppGroup(MicroAppGroup microAppGroup) {
        this.microAppGroups.add(microAppGroup);
        microAppGroup.getFmpMicroApps().add(this);
        return this;
    }

    public FmpMicroApp removeMicroAppGroup(MicroAppGroup microAppGroup) {
        this.microAppGroups.remove(microAppGroup);
        microAppGroup.getFmpMicroApps().remove(this);
        return this;
    }

    public void setMicroAppGroups(Set<MicroAppGroup> microAppGroups) {
        this.microAppGroups = microAppGroups;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FmpMicroApp)) {
            return false;
        }
        return id != null && id.equals(((FmpMicroApp) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FmpMicroApp{" +
            "id=" + getId() +
            ", catCode='" + getCatCode() + "'" +
            ", parentCatId='" + getParentCatId() + "'" +
            ", levelNumber=" + getLevelNumber() +
            ", isLeaf='" + getIsLeaf() + "'" +
            ", businessCode='" + getBusinessCode() + "'" +
            ", endpointUrl='" + getEndpointUrl() + "'" +
            ", iconImg='" + getIconImg() + "'" +
            ", bannerImg='" + getBannerImg() + "'" +
            ", thumbnail='" + getThumbnail() + "'" +
            ", sortCode='" + getSortCode() + "'" +
            ", systemType='" + getSystemType() + "'" +
            ", contentOwnerCode='" + getContentOwnerCode() + "'" +
            ", lable='" + getLable() + "'" +
            ", isNew='" + getIsNew() + "'" +
            ", status='" + getStatus() + "'" +
            ", openMethod='" + getOpenMethod() + "'" +
            ", language='" + getLanguage() + "'" +
            ", isFixed='" + getIsFixed() + "'" +
            ", tagKey01='" + getTagKey01() + "'" +
            ", tagVal01='" + getTagVal01() + "'" +
            ", tagKey02='" + getTagKey02() + "'" +
            ", tagVal02='" + getTagVal02() + "'" +
            ", tagKey03='" + getTagKey03() + "'" +
            ", tagVal03='" + getTagVal03() + "'" +
            ", catName='" + getCatName() + "'" +
            ", filterSql='" + getFilterSql() + "'" +
            ", sharingFlag='" + getSharingFlag() + "'" +
            ", category='" + getCategory() + "'" +
            ", imgClass='" + getImgClass() + "'" +
            ", isInternal='" + getIsInternal() + "'" +
            ", customFlag='" + getCustomFlag() + "'" +
            ", portal='" + getPortal() + "'" +
            ", description='" + getDescription() + "'" +
            ", businessUnit='" + getBusinessUnit() + "'" +
            ", businessOwners='" + getBusinessOwners() + "'" +
            ", businessOwnersMobile='" + getBusinessOwnersMobile() + "'" +
            "}";
    }
}
