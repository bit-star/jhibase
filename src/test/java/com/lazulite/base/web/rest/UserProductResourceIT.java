package com.lazulite.base.web.rest;

import com.lazulite.base.JhibaseApp;
import com.lazulite.base.domain.UserProduct;
import com.lazulite.base.repository.UserProductRepository;
import com.lazulite.base.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.lazulite.base.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UserProductResource} REST controller.
 */
@SpringBootTest(classes = JhibaseApp.class)
public class UserProductResourceIT {

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DES = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DES = "BBBBBBBBBB";

    private static final String DEFAULT_IMG_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMG_URL = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_CREATED_DATE = Instant.ofEpochMilli(-1L);

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);
    private static final Instant SMALLER_UPDATE_DATE = Instant.ofEpochMilli(-1L);

    @Autowired
    private UserProductRepository userProductRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restUserProductMockMvc;

    private UserProduct userProduct;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UserProductResource userProductResource = new UserProductResource(userProductRepository);
        this.restUserProductMockMvc = MockMvcBuilders.standaloneSetup(userProductResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserProduct createEntity(EntityManager em) {
        UserProduct userProduct = new UserProduct()
            .productName(DEFAULT_PRODUCT_NAME)
            .productDes(DEFAULT_PRODUCT_DES)
            .imgURL(DEFAULT_IMG_URL)
            .createdDate(DEFAULT_CREATED_DATE)
            .updateDate(DEFAULT_UPDATE_DATE);
        return userProduct;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserProduct createUpdatedEntity(EntityManager em) {
        UserProduct userProduct = new UserProduct()
            .productName(UPDATED_PRODUCT_NAME)
            .productDes(UPDATED_PRODUCT_DES)
            .imgURL(UPDATED_IMG_URL)
            .createdDate(UPDATED_CREATED_DATE)
            .updateDate(UPDATED_UPDATE_DATE);
        return userProduct;
    }

    @BeforeEach
    public void initTest() {
        userProduct = createEntity(em);
    }

    @Test
    @Transactional
    public void createUserProduct() throws Exception {
        int databaseSizeBeforeCreate = userProductRepository.findAll().size();

        // Create the UserProduct
        restUserProductMockMvc.perform(post("/api/user-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProduct)))
            .andExpect(status().isCreated());

        // Validate the UserProduct in the database
        List<UserProduct> userProductList = userProductRepository.findAll();
        assertThat(userProductList).hasSize(databaseSizeBeforeCreate + 1);
        UserProduct testUserProduct = userProductList.get(userProductList.size() - 1);
        assertThat(testUserProduct.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testUserProduct.getProductDes()).isEqualTo(DEFAULT_PRODUCT_DES);
        assertThat(testUserProduct.getImgURL()).isEqualTo(DEFAULT_IMG_URL);
        assertThat(testUserProduct.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserProduct.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void createUserProductWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = userProductRepository.findAll().size();

        // Create the UserProduct with an existing ID
        userProduct.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserProductMockMvc.perform(post("/api/user-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProduct)))
            .andExpect(status().isBadRequest());

        // Validate the UserProduct in the database
        List<UserProduct> userProductList = userProductRepository.findAll();
        assertThat(userProductList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUserProducts() throws Exception {
        // Initialize the database
        userProductRepository.saveAndFlush(userProduct);

        // Get all the userProductList
        restUserProductMockMvc.perform(get("/api/user-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userProduct.getId().intValue())))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME.toString())))
            .andExpect(jsonPath("$.[*].productDes").value(hasItem(DEFAULT_PRODUCT_DES.toString())))
            .andExpect(jsonPath("$.[*].imgURL").value(hasItem(DEFAULT_IMG_URL.toString())))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getUserProduct() throws Exception {
        // Initialize the database
        userProductRepository.saveAndFlush(userProduct);

        // Get the userProduct
        restUserProductMockMvc.perform(get("/api/user-products/{id}", userProduct.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(userProduct.getId().intValue()))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME.toString()))
            .andExpect(jsonPath("$.productDes").value(DEFAULT_PRODUCT_DES.toString()))
            .andExpect(jsonPath("$.imgURL").value(DEFAULT_IMG_URL.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUserProduct() throws Exception {
        // Get the userProduct
        restUserProductMockMvc.perform(get("/api/user-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUserProduct() throws Exception {
        // Initialize the database
        userProductRepository.saveAndFlush(userProduct);

        int databaseSizeBeforeUpdate = userProductRepository.findAll().size();

        // Update the userProduct
        UserProduct updatedUserProduct = userProductRepository.findById(userProduct.getId()).get();
        // Disconnect from session so that the updates on updatedUserProduct are not directly saved in db
        em.detach(updatedUserProduct);
        updatedUserProduct
            .productName(UPDATED_PRODUCT_NAME)
            .productDes(UPDATED_PRODUCT_DES)
            .imgURL(UPDATED_IMG_URL)
            .createdDate(UPDATED_CREATED_DATE)
            .updateDate(UPDATED_UPDATE_DATE);

        restUserProductMockMvc.perform(put("/api/user-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedUserProduct)))
            .andExpect(status().isOk());

        // Validate the UserProduct in the database
        List<UserProduct> userProductList = userProductRepository.findAll();
        assertThat(userProductList).hasSize(databaseSizeBeforeUpdate);
        UserProduct testUserProduct = userProductList.get(userProductList.size() - 1);
        assertThat(testUserProduct.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testUserProduct.getProductDes()).isEqualTo(UPDATED_PRODUCT_DES);
        assertThat(testUserProduct.getImgURL()).isEqualTo(UPDATED_IMG_URL);
        assertThat(testUserProduct.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserProduct.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingUserProduct() throws Exception {
        int databaseSizeBeforeUpdate = userProductRepository.findAll().size();

        // Create the UserProduct

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserProductMockMvc.perform(put("/api/user-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userProduct)))
            .andExpect(status().isBadRequest());

        // Validate the UserProduct in the database
        List<UserProduct> userProductList = userProductRepository.findAll();
        assertThat(userProductList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUserProduct() throws Exception {
        // Initialize the database
        userProductRepository.saveAndFlush(userProduct);

        int databaseSizeBeforeDelete = userProductRepository.findAll().size();

        // Delete the userProduct
        restUserProductMockMvc.perform(delete("/api/user-products/{id}", userProduct.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserProduct> userProductList = userProductRepository.findAll();
        assertThat(userProductList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserProduct.class);
        UserProduct userProduct1 = new UserProduct();
        userProduct1.setId(1L);
        UserProduct userProduct2 = new UserProduct();
        userProduct2.setId(userProduct1.getId());
        assertThat(userProduct1).isEqualTo(userProduct2);
        userProduct2.setId(2L);
        assertThat(userProduct1).isNotEqualTo(userProduct2);
        userProduct1.setId(null);
        assertThat(userProduct1).isNotEqualTo(userProduct2);
    }
}
