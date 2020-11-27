package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.FmpMicroApp;
import com.lazulite.base.repository.FmpMicroAppRepository;
import com.lazulite.base.service.FmpMicroAppService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lazulite.base.domain.enumeration.IsNew;
import com.lazulite.base.domain.enumeration.MicroAppStatus;
import com.lazulite.base.domain.enumeration.OpenMethod;
import com.lazulite.base.domain.enumeration.Language;
import com.lazulite.base.domain.enumeration.IsFixed;
/**
 * Integration tests for the {@link FmpMicroAppResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class FmpMicroAppResourceIT {

    private static final String DEFAULT_CAT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CAT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_CAT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_CAT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_LEVEL_NUMBER = 1;
    private static final Integer UPDATED_LEVEL_NUMBER = 2;

    private static final String DEFAULT_IS_LEAF = "AAAAAAAAAA";
    private static final String UPDATED_IS_LEAF = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ENDPOINT_URL = "AAAAAAAAAA";
    private static final String UPDATED_ENDPOINT_URL = "BBBBBBBBBB";

    private static final String DEFAULT_ICON_IMG = "AAAAAAAAAA";
    private static final String UPDATED_ICON_IMG = "BBBBBBBBBB";

    private static final String DEFAULT_BANNER_IMG = "AAAAAAAAAA";
    private static final String UPDATED_BANNER_IMG = "BBBBBBBBBB";

    private static final String DEFAULT_THUMBNAIL = "AAAAAAAAAA";
    private static final String UPDATED_THUMBNAIL = "BBBBBBBBBB";

    private static final String DEFAULT_SORT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SORT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_OWNER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_OWNER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LABLE = "AAAAAAAAAA";
    private static final String UPDATED_LABLE = "BBBBBBBBBB";

    private static final IsNew DEFAULT_IS_NEW = IsNew.New;
    private static final IsNew UPDATED_IS_NEW = IsNew.NotNew;

    private static final MicroAppStatus DEFAULT_STATUS = MicroAppStatus.OffLine;
    private static final MicroAppStatus UPDATED_STATUS = MicroAppStatus.OnLIne;

    private static final OpenMethod DEFAULT_OPEN_METHOD = OpenMethod.Nesting;
    private static final OpenMethod UPDATED_OPEN_METHOD = OpenMethod.PopUp;

    private static final Language DEFAULT_LANGUAGE = Language.ZH;
    private static final Language UPDATED_LANGUAGE = Language.EN;

    private static final IsFixed DEFAULT_IS_FIXED = IsFixed.Fixed;
    private static final IsFixed UPDATED_IS_FIXED = IsFixed.NotFixed;

    private static final String DEFAULT_TAG_KEY_01 = "AAAAAAAAAA";
    private static final String UPDATED_TAG_KEY_01 = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_VAL_01 = "AAAAAAAAAA";
    private static final String UPDATED_TAG_VAL_01 = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_KEY_02 = "AAAAAAAAAA";
    private static final String UPDATED_TAG_KEY_02 = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_VAL_02 = "AAAAAAAAAA";
    private static final String UPDATED_TAG_VAL_02 = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_KEY_03 = "AAAAAAAAAA";
    private static final String UPDATED_TAG_KEY_03 = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_VAL_03 = "AAAAAAAAAA";
    private static final String UPDATED_TAG_VAL_03 = "BBBBBBBBBB";

    private static final String DEFAULT_CAT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILTER_SQL = "AAAAAAAAAA";
    private static final String UPDATED_FILTER_SQL = "BBBBBBBBBB";

    private static final String DEFAULT_SHARING_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_SHARING_FLAG = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_IMG_CLASS = "AAAAAAAAAA";
    private static final String UPDATED_IMG_CLASS = "BBBBBBBBBB";

    private static final String DEFAULT_IS_INTERNAL = "AAAAAAAAAA";
    private static final String UPDATED_IS_INTERNAL = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOM_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOM_FLAG = "BBBBBBBBBB";

    private static final String DEFAULT_PORTAL = "AAAAAAAAAA";
    private static final String UPDATED_PORTAL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_UNIT = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_OWNERS = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_OWNERS = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_OWNERS_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_OWNERS_MOBILE = "BBBBBBBBBB";

    @Autowired
    private FmpMicroAppRepository fmpMicroAppRepository;

    @Mock
    private FmpMicroAppRepository fmpMicroAppRepositoryMock;

    @Mock
    private FmpMicroAppService fmpMicroAppServiceMock;

    @Autowired
    private FmpMicroAppService fmpMicroAppService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFmpMicroAppMockMvc;

    private FmpMicroApp fmpMicroApp;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FmpMicroApp createEntity(EntityManager em) {
        FmpMicroApp fmpMicroApp = new FmpMicroApp()
            .catCode(DEFAULT_CAT_CODE)
            .parentCatId(DEFAULT_PARENT_CAT_ID)
            .levelNumber(DEFAULT_LEVEL_NUMBER)
            .isLeaf(DEFAULT_IS_LEAF)
            .businessCode(DEFAULT_BUSINESS_CODE)
            .endpointUrl(DEFAULT_ENDPOINT_URL)
            .iconImg(DEFAULT_ICON_IMG)
            .bannerImg(DEFAULT_BANNER_IMG)
            .thumbnail(DEFAULT_THUMBNAIL)
            .sortCode(DEFAULT_SORT_CODE)
            .systemType(DEFAULT_SYSTEM_TYPE)
            .contentOwnerCode(DEFAULT_CONTENT_OWNER_CODE)
            .lable(DEFAULT_LABLE)
            .isNew(DEFAULT_IS_NEW)
            .status(DEFAULT_STATUS)
            .openMethod(DEFAULT_OPEN_METHOD)
            .language(DEFAULT_LANGUAGE)
            .isFixed(DEFAULT_IS_FIXED)
            .tagKey01(DEFAULT_TAG_KEY_01)
            .tagVal01(DEFAULT_TAG_VAL_01)
            .tagKey02(DEFAULT_TAG_KEY_02)
            .tagVal02(DEFAULT_TAG_VAL_02)
            .tagKey03(DEFAULT_TAG_KEY_03)
            .tagVal03(DEFAULT_TAG_VAL_03)
            .catName(DEFAULT_CAT_NAME)
            .filterSql(DEFAULT_FILTER_SQL)
            .sharingFlag(DEFAULT_SHARING_FLAG)
            .category(DEFAULT_CATEGORY)
            .imgClass(DEFAULT_IMG_CLASS)
            .isInternal(DEFAULT_IS_INTERNAL)
            .customFlag(DEFAULT_CUSTOM_FLAG)
            .portal(DEFAULT_PORTAL)
            .description(DEFAULT_DESCRIPTION)
            .businessUnit(DEFAULT_BUSINESS_UNIT)
            .businessOwners(DEFAULT_BUSINESS_OWNERS)
            .businessOwnersMobile(DEFAULT_BUSINESS_OWNERS_MOBILE);
        return fmpMicroApp;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FmpMicroApp createUpdatedEntity(EntityManager em) {
        FmpMicroApp fmpMicroApp = new FmpMicroApp()
            .catCode(UPDATED_CAT_CODE)
            .parentCatId(UPDATED_PARENT_CAT_ID)
            .levelNumber(UPDATED_LEVEL_NUMBER)
            .isLeaf(UPDATED_IS_LEAF)
            .businessCode(UPDATED_BUSINESS_CODE)
            .endpointUrl(UPDATED_ENDPOINT_URL)
            .iconImg(UPDATED_ICON_IMG)
            .bannerImg(UPDATED_BANNER_IMG)
            .thumbnail(UPDATED_THUMBNAIL)
            .sortCode(UPDATED_SORT_CODE)
            .systemType(UPDATED_SYSTEM_TYPE)
            .contentOwnerCode(UPDATED_CONTENT_OWNER_CODE)
            .lable(UPDATED_LABLE)
            .isNew(UPDATED_IS_NEW)
            .status(UPDATED_STATUS)
            .openMethod(UPDATED_OPEN_METHOD)
            .language(UPDATED_LANGUAGE)
            .isFixed(UPDATED_IS_FIXED)
            .tagKey01(UPDATED_TAG_KEY_01)
            .tagVal01(UPDATED_TAG_VAL_01)
            .tagKey02(UPDATED_TAG_KEY_02)
            .tagVal02(UPDATED_TAG_VAL_02)
            .tagKey03(UPDATED_TAG_KEY_03)
            .tagVal03(UPDATED_TAG_VAL_03)
            .catName(UPDATED_CAT_NAME)
            .filterSql(UPDATED_FILTER_SQL)
            .sharingFlag(UPDATED_SHARING_FLAG)
            .category(UPDATED_CATEGORY)
            .imgClass(UPDATED_IMG_CLASS)
            .isInternal(UPDATED_IS_INTERNAL)
            .customFlag(UPDATED_CUSTOM_FLAG)
            .portal(UPDATED_PORTAL)
            .description(UPDATED_DESCRIPTION)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .businessOwners(UPDATED_BUSINESS_OWNERS)
            .businessOwnersMobile(UPDATED_BUSINESS_OWNERS_MOBILE);
        return fmpMicroApp;
    }

    @BeforeEach
    public void initTest() {
        fmpMicroApp = createEntity(em);
    }

    @Test
    @Transactional
    public void createFmpMicroApp() throws Exception {
        int databaseSizeBeforeCreate = fmpMicroAppRepository.findAll().size();
        // Create the FmpMicroApp
        restFmpMicroAppMockMvc.perform(post("/api/fmp-micro-apps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fmpMicroApp)))
            .andExpect(status().isCreated());

        // Validate the FmpMicroApp in the database
        List<FmpMicroApp> fmpMicroAppList = fmpMicroAppRepository.findAll();
        assertThat(fmpMicroAppList).hasSize(databaseSizeBeforeCreate + 1);
        FmpMicroApp testFmpMicroApp = fmpMicroAppList.get(fmpMicroAppList.size() - 1);
        assertThat(testFmpMicroApp.getCatCode()).isEqualTo(DEFAULT_CAT_CODE);
        assertThat(testFmpMicroApp.getParentCatId()).isEqualTo(DEFAULT_PARENT_CAT_ID);
        assertThat(testFmpMicroApp.getLevelNumber()).isEqualTo(DEFAULT_LEVEL_NUMBER);
        assertThat(testFmpMicroApp.getIsLeaf()).isEqualTo(DEFAULT_IS_LEAF);
        assertThat(testFmpMicroApp.getBusinessCode()).isEqualTo(DEFAULT_BUSINESS_CODE);
        assertThat(testFmpMicroApp.getEndpointUrl()).isEqualTo(DEFAULT_ENDPOINT_URL);
        assertThat(testFmpMicroApp.getIconImg()).isEqualTo(DEFAULT_ICON_IMG);
        assertThat(testFmpMicroApp.getBannerImg()).isEqualTo(DEFAULT_BANNER_IMG);
        assertThat(testFmpMicroApp.getThumbnail()).isEqualTo(DEFAULT_THUMBNAIL);
        assertThat(testFmpMicroApp.getSortCode()).isEqualTo(DEFAULT_SORT_CODE);
        assertThat(testFmpMicroApp.getSystemType()).isEqualTo(DEFAULT_SYSTEM_TYPE);
        assertThat(testFmpMicroApp.getContentOwnerCode()).isEqualTo(DEFAULT_CONTENT_OWNER_CODE);
        assertThat(testFmpMicroApp.getLable()).isEqualTo(DEFAULT_LABLE);
        assertThat(testFmpMicroApp.getIsNew()).isEqualTo(DEFAULT_IS_NEW);
        assertThat(testFmpMicroApp.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFmpMicroApp.getOpenMethod()).isEqualTo(DEFAULT_OPEN_METHOD);
        assertThat(testFmpMicroApp.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testFmpMicroApp.getIsFixed()).isEqualTo(DEFAULT_IS_FIXED);
        assertThat(testFmpMicroApp.getTagKey01()).isEqualTo(DEFAULT_TAG_KEY_01);
        assertThat(testFmpMicroApp.getTagVal01()).isEqualTo(DEFAULT_TAG_VAL_01);
        assertThat(testFmpMicroApp.getTagKey02()).isEqualTo(DEFAULT_TAG_KEY_02);
        assertThat(testFmpMicroApp.getTagVal02()).isEqualTo(DEFAULT_TAG_VAL_02);
        assertThat(testFmpMicroApp.getTagKey03()).isEqualTo(DEFAULT_TAG_KEY_03);
        assertThat(testFmpMicroApp.getTagVal03()).isEqualTo(DEFAULT_TAG_VAL_03);
        assertThat(testFmpMicroApp.getCatName()).isEqualTo(DEFAULT_CAT_NAME);
        assertThat(testFmpMicroApp.getFilterSql()).isEqualTo(DEFAULT_FILTER_SQL);
        assertThat(testFmpMicroApp.getSharingFlag()).isEqualTo(DEFAULT_SHARING_FLAG);
        assertThat(testFmpMicroApp.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testFmpMicroApp.getImgClass()).isEqualTo(DEFAULT_IMG_CLASS);
        assertThat(testFmpMicroApp.getIsInternal()).isEqualTo(DEFAULT_IS_INTERNAL);
        assertThat(testFmpMicroApp.getCustomFlag()).isEqualTo(DEFAULT_CUSTOM_FLAG);
        assertThat(testFmpMicroApp.getPortal()).isEqualTo(DEFAULT_PORTAL);
        assertThat(testFmpMicroApp.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testFmpMicroApp.getBusinessUnit()).isEqualTo(DEFAULT_BUSINESS_UNIT);
        assertThat(testFmpMicroApp.getBusinessOwners()).isEqualTo(DEFAULT_BUSINESS_OWNERS);
        assertThat(testFmpMicroApp.getBusinessOwnersMobile()).isEqualTo(DEFAULT_BUSINESS_OWNERS_MOBILE);
    }

    @Test
    @Transactional
    public void createFmpMicroAppWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fmpMicroAppRepository.findAll().size();

        // Create the FmpMicroApp with an existing ID
        fmpMicroApp.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFmpMicroAppMockMvc.perform(post("/api/fmp-micro-apps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fmpMicroApp)))
            .andExpect(status().isBadRequest());

        // Validate the FmpMicroApp in the database
        List<FmpMicroApp> fmpMicroAppList = fmpMicroAppRepository.findAll();
        assertThat(fmpMicroAppList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFmpMicroApps() throws Exception {
        // Initialize the database
        fmpMicroAppRepository.saveAndFlush(fmpMicroApp);

        // Get all the fmpMicroAppList
        restFmpMicroAppMockMvc.perform(get("/api/fmp-micro-apps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fmpMicroApp.getId().intValue())))
            .andExpect(jsonPath("$.[*].catCode").value(hasItem(DEFAULT_CAT_CODE)))
            .andExpect(jsonPath("$.[*].parentCatId").value(hasItem(DEFAULT_PARENT_CAT_ID)))
            .andExpect(jsonPath("$.[*].levelNumber").value(hasItem(DEFAULT_LEVEL_NUMBER)))
            .andExpect(jsonPath("$.[*].isLeaf").value(hasItem(DEFAULT_IS_LEAF)))
            .andExpect(jsonPath("$.[*].businessCode").value(hasItem(DEFAULT_BUSINESS_CODE)))
            .andExpect(jsonPath("$.[*].endpointUrl").value(hasItem(DEFAULT_ENDPOINT_URL)))
            .andExpect(jsonPath("$.[*].iconImg").value(hasItem(DEFAULT_ICON_IMG)))
            .andExpect(jsonPath("$.[*].bannerImg").value(hasItem(DEFAULT_BANNER_IMG)))
            .andExpect(jsonPath("$.[*].thumbnail").value(hasItem(DEFAULT_THUMBNAIL)))
            .andExpect(jsonPath("$.[*].sortCode").value(hasItem(DEFAULT_SORT_CODE)))
            .andExpect(jsonPath("$.[*].systemType").value(hasItem(DEFAULT_SYSTEM_TYPE)))
            .andExpect(jsonPath("$.[*].contentOwnerCode").value(hasItem(DEFAULT_CONTENT_OWNER_CODE)))
            .andExpect(jsonPath("$.[*].lable").value(hasItem(DEFAULT_LABLE)))
            .andExpect(jsonPath("$.[*].isNew").value(hasItem(DEFAULT_IS_NEW.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].openMethod").value(hasItem(DEFAULT_OPEN_METHOD.toString())))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
            .andExpect(jsonPath("$.[*].isFixed").value(hasItem(DEFAULT_IS_FIXED.toString())))
            .andExpect(jsonPath("$.[*].tagKey01").value(hasItem(DEFAULT_TAG_KEY_01)))
            .andExpect(jsonPath("$.[*].tagVal01").value(hasItem(DEFAULT_TAG_VAL_01)))
            .andExpect(jsonPath("$.[*].tagKey02").value(hasItem(DEFAULT_TAG_KEY_02)))
            .andExpect(jsonPath("$.[*].tagVal02").value(hasItem(DEFAULT_TAG_VAL_02)))
            .andExpect(jsonPath("$.[*].tagKey03").value(hasItem(DEFAULT_TAG_KEY_03)))
            .andExpect(jsonPath("$.[*].tagVal03").value(hasItem(DEFAULT_TAG_VAL_03)))
            .andExpect(jsonPath("$.[*].catName").value(hasItem(DEFAULT_CAT_NAME)))
            .andExpect(jsonPath("$.[*].filterSql").value(hasItem(DEFAULT_FILTER_SQL)))
            .andExpect(jsonPath("$.[*].sharingFlag").value(hasItem(DEFAULT_SHARING_FLAG)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
            .andExpect(jsonPath("$.[*].imgClass").value(hasItem(DEFAULT_IMG_CLASS)))
            .andExpect(jsonPath("$.[*].isInternal").value(hasItem(DEFAULT_IS_INTERNAL)))
            .andExpect(jsonPath("$.[*].customFlag").value(hasItem(DEFAULT_CUSTOM_FLAG)))
            .andExpect(jsonPath("$.[*].portal").value(hasItem(DEFAULT_PORTAL)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].businessUnit").value(hasItem(DEFAULT_BUSINESS_UNIT)))
            .andExpect(jsonPath("$.[*].businessOwners").value(hasItem(DEFAULT_BUSINESS_OWNERS)))
            .andExpect(jsonPath("$.[*].businessOwnersMobile").value(hasItem(DEFAULT_BUSINESS_OWNERS_MOBILE)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllFmpMicroAppsWithEagerRelationshipsIsEnabled() throws Exception {
        when(fmpMicroAppServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFmpMicroAppMockMvc.perform(get("/api/fmp-micro-apps?eagerload=true"))
            .andExpect(status().isOk());

        verify(fmpMicroAppServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllFmpMicroAppsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(fmpMicroAppServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restFmpMicroAppMockMvc.perform(get("/api/fmp-micro-apps?eagerload=true"))
            .andExpect(status().isOk());

        verify(fmpMicroAppServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getFmpMicroApp() throws Exception {
        // Initialize the database
        fmpMicroAppRepository.saveAndFlush(fmpMicroApp);

        // Get the fmpMicroApp
        restFmpMicroAppMockMvc.perform(get("/api/fmp-micro-apps/{id}", fmpMicroApp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fmpMicroApp.getId().intValue()))
            .andExpect(jsonPath("$.catCode").value(DEFAULT_CAT_CODE))
            .andExpect(jsonPath("$.parentCatId").value(DEFAULT_PARENT_CAT_ID))
            .andExpect(jsonPath("$.levelNumber").value(DEFAULT_LEVEL_NUMBER))
            .andExpect(jsonPath("$.isLeaf").value(DEFAULT_IS_LEAF))
            .andExpect(jsonPath("$.businessCode").value(DEFAULT_BUSINESS_CODE))
            .andExpect(jsonPath("$.endpointUrl").value(DEFAULT_ENDPOINT_URL))
            .andExpect(jsonPath("$.iconImg").value(DEFAULT_ICON_IMG))
            .andExpect(jsonPath("$.bannerImg").value(DEFAULT_BANNER_IMG))
            .andExpect(jsonPath("$.thumbnail").value(DEFAULT_THUMBNAIL))
            .andExpect(jsonPath("$.sortCode").value(DEFAULT_SORT_CODE))
            .andExpect(jsonPath("$.systemType").value(DEFAULT_SYSTEM_TYPE))
            .andExpect(jsonPath("$.contentOwnerCode").value(DEFAULT_CONTENT_OWNER_CODE))
            .andExpect(jsonPath("$.lable").value(DEFAULT_LABLE))
            .andExpect(jsonPath("$.isNew").value(DEFAULT_IS_NEW.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.openMethod").value(DEFAULT_OPEN_METHOD.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.isFixed").value(DEFAULT_IS_FIXED.toString()))
            .andExpect(jsonPath("$.tagKey01").value(DEFAULT_TAG_KEY_01))
            .andExpect(jsonPath("$.tagVal01").value(DEFAULT_TAG_VAL_01))
            .andExpect(jsonPath("$.tagKey02").value(DEFAULT_TAG_KEY_02))
            .andExpect(jsonPath("$.tagVal02").value(DEFAULT_TAG_VAL_02))
            .andExpect(jsonPath("$.tagKey03").value(DEFAULT_TAG_KEY_03))
            .andExpect(jsonPath("$.tagVal03").value(DEFAULT_TAG_VAL_03))
            .andExpect(jsonPath("$.catName").value(DEFAULT_CAT_NAME))
            .andExpect(jsonPath("$.filterSql").value(DEFAULT_FILTER_SQL))
            .andExpect(jsonPath("$.sharingFlag").value(DEFAULT_SHARING_FLAG))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
            .andExpect(jsonPath("$.imgClass").value(DEFAULT_IMG_CLASS))
            .andExpect(jsonPath("$.isInternal").value(DEFAULT_IS_INTERNAL))
            .andExpect(jsonPath("$.customFlag").value(DEFAULT_CUSTOM_FLAG))
            .andExpect(jsonPath("$.portal").value(DEFAULT_PORTAL))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.businessUnit").value(DEFAULT_BUSINESS_UNIT))
            .andExpect(jsonPath("$.businessOwners").value(DEFAULT_BUSINESS_OWNERS))
            .andExpect(jsonPath("$.businessOwnersMobile").value(DEFAULT_BUSINESS_OWNERS_MOBILE));
    }
    @Test
    @Transactional
    public void getNonExistingFmpMicroApp() throws Exception {
        // Get the fmpMicroApp
        restFmpMicroAppMockMvc.perform(get("/api/fmp-micro-apps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFmpMicroApp() throws Exception {
        // Initialize the database
        fmpMicroAppService.save(fmpMicroApp);

        int databaseSizeBeforeUpdate = fmpMicroAppRepository.findAll().size();

        // Update the fmpMicroApp
        FmpMicroApp updatedFmpMicroApp = fmpMicroAppRepository.findById(fmpMicroApp.getId()).get();
        // Disconnect from session so that the updates on updatedFmpMicroApp are not directly saved in db
        em.detach(updatedFmpMicroApp);
        updatedFmpMicroApp
            .catCode(UPDATED_CAT_CODE)
            .parentCatId(UPDATED_PARENT_CAT_ID)
            .levelNumber(UPDATED_LEVEL_NUMBER)
            .isLeaf(UPDATED_IS_LEAF)
            .businessCode(UPDATED_BUSINESS_CODE)
            .endpointUrl(UPDATED_ENDPOINT_URL)
            .iconImg(UPDATED_ICON_IMG)
            .bannerImg(UPDATED_BANNER_IMG)
            .thumbnail(UPDATED_THUMBNAIL)
            .sortCode(UPDATED_SORT_CODE)
            .systemType(UPDATED_SYSTEM_TYPE)
            .contentOwnerCode(UPDATED_CONTENT_OWNER_CODE)
            .lable(UPDATED_LABLE)
            .isNew(UPDATED_IS_NEW)
            .status(UPDATED_STATUS)
            .openMethod(UPDATED_OPEN_METHOD)
            .language(UPDATED_LANGUAGE)
            .isFixed(UPDATED_IS_FIXED)
            .tagKey01(UPDATED_TAG_KEY_01)
            .tagVal01(UPDATED_TAG_VAL_01)
            .tagKey02(UPDATED_TAG_KEY_02)
            .tagVal02(UPDATED_TAG_VAL_02)
            .tagKey03(UPDATED_TAG_KEY_03)
            .tagVal03(UPDATED_TAG_VAL_03)
            .catName(UPDATED_CAT_NAME)
            .filterSql(UPDATED_FILTER_SQL)
            .sharingFlag(UPDATED_SHARING_FLAG)
            .category(UPDATED_CATEGORY)
            .imgClass(UPDATED_IMG_CLASS)
            .isInternal(UPDATED_IS_INTERNAL)
            .customFlag(UPDATED_CUSTOM_FLAG)
            .portal(UPDATED_PORTAL)
            .description(UPDATED_DESCRIPTION)
            .businessUnit(UPDATED_BUSINESS_UNIT)
            .businessOwners(UPDATED_BUSINESS_OWNERS)
            .businessOwnersMobile(UPDATED_BUSINESS_OWNERS_MOBILE);

        restFmpMicroAppMockMvc.perform(put("/api/fmp-micro-apps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFmpMicroApp)))
            .andExpect(status().isOk());

        // Validate the FmpMicroApp in the database
        List<FmpMicroApp> fmpMicroAppList = fmpMicroAppRepository.findAll();
        assertThat(fmpMicroAppList).hasSize(databaseSizeBeforeUpdate);
        FmpMicroApp testFmpMicroApp = fmpMicroAppList.get(fmpMicroAppList.size() - 1);
        assertThat(testFmpMicroApp.getCatCode()).isEqualTo(UPDATED_CAT_CODE);
        assertThat(testFmpMicroApp.getParentCatId()).isEqualTo(UPDATED_PARENT_CAT_ID);
        assertThat(testFmpMicroApp.getLevelNumber()).isEqualTo(UPDATED_LEVEL_NUMBER);
        assertThat(testFmpMicroApp.getIsLeaf()).isEqualTo(UPDATED_IS_LEAF);
        assertThat(testFmpMicroApp.getBusinessCode()).isEqualTo(UPDATED_BUSINESS_CODE);
        assertThat(testFmpMicroApp.getEndpointUrl()).isEqualTo(UPDATED_ENDPOINT_URL);
        assertThat(testFmpMicroApp.getIconImg()).isEqualTo(UPDATED_ICON_IMG);
        assertThat(testFmpMicroApp.getBannerImg()).isEqualTo(UPDATED_BANNER_IMG);
        assertThat(testFmpMicroApp.getThumbnail()).isEqualTo(UPDATED_THUMBNAIL);
        assertThat(testFmpMicroApp.getSortCode()).isEqualTo(UPDATED_SORT_CODE);
        assertThat(testFmpMicroApp.getSystemType()).isEqualTo(UPDATED_SYSTEM_TYPE);
        assertThat(testFmpMicroApp.getContentOwnerCode()).isEqualTo(UPDATED_CONTENT_OWNER_CODE);
        assertThat(testFmpMicroApp.getLable()).isEqualTo(UPDATED_LABLE);
        assertThat(testFmpMicroApp.getIsNew()).isEqualTo(UPDATED_IS_NEW);
        assertThat(testFmpMicroApp.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFmpMicroApp.getOpenMethod()).isEqualTo(UPDATED_OPEN_METHOD);
        assertThat(testFmpMicroApp.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testFmpMicroApp.getIsFixed()).isEqualTo(UPDATED_IS_FIXED);
        assertThat(testFmpMicroApp.getTagKey01()).isEqualTo(UPDATED_TAG_KEY_01);
        assertThat(testFmpMicroApp.getTagVal01()).isEqualTo(UPDATED_TAG_VAL_01);
        assertThat(testFmpMicroApp.getTagKey02()).isEqualTo(UPDATED_TAG_KEY_02);
        assertThat(testFmpMicroApp.getTagVal02()).isEqualTo(UPDATED_TAG_VAL_02);
        assertThat(testFmpMicroApp.getTagKey03()).isEqualTo(UPDATED_TAG_KEY_03);
        assertThat(testFmpMicroApp.getTagVal03()).isEqualTo(UPDATED_TAG_VAL_03);
        assertThat(testFmpMicroApp.getCatName()).isEqualTo(UPDATED_CAT_NAME);
        assertThat(testFmpMicroApp.getFilterSql()).isEqualTo(UPDATED_FILTER_SQL);
        assertThat(testFmpMicroApp.getSharingFlag()).isEqualTo(UPDATED_SHARING_FLAG);
        assertThat(testFmpMicroApp.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testFmpMicroApp.getImgClass()).isEqualTo(UPDATED_IMG_CLASS);
        assertThat(testFmpMicroApp.getIsInternal()).isEqualTo(UPDATED_IS_INTERNAL);
        assertThat(testFmpMicroApp.getCustomFlag()).isEqualTo(UPDATED_CUSTOM_FLAG);
        assertThat(testFmpMicroApp.getPortal()).isEqualTo(UPDATED_PORTAL);
        assertThat(testFmpMicroApp.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testFmpMicroApp.getBusinessUnit()).isEqualTo(UPDATED_BUSINESS_UNIT);
        assertThat(testFmpMicroApp.getBusinessOwners()).isEqualTo(UPDATED_BUSINESS_OWNERS);
        assertThat(testFmpMicroApp.getBusinessOwnersMobile()).isEqualTo(UPDATED_BUSINESS_OWNERS_MOBILE);
    }

    @Test
    @Transactional
    public void updateNonExistingFmpMicroApp() throws Exception {
        int databaseSizeBeforeUpdate = fmpMicroAppRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFmpMicroAppMockMvc.perform(put("/api/fmp-micro-apps")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fmpMicroApp)))
            .andExpect(status().isBadRequest());

        // Validate the FmpMicroApp in the database
        List<FmpMicroApp> fmpMicroAppList = fmpMicroAppRepository.findAll();
        assertThat(fmpMicroAppList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFmpMicroApp() throws Exception {
        // Initialize the database
        fmpMicroAppService.save(fmpMicroApp);

        int databaseSizeBeforeDelete = fmpMicroAppRepository.findAll().size();

        // Delete the fmpMicroApp
        restFmpMicroAppMockMvc.perform(delete("/api/fmp-micro-apps/{id}", fmpMicroApp.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FmpMicroApp> fmpMicroAppList = fmpMicroAppRepository.findAll();
        assertThat(fmpMicroAppList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
