package br.com.labrador.web.rest;

import br.com.labrador.LabradorApp;

import br.com.labrador.domain.Bone;
import br.com.labrador.repository.BoneRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.labrador.domain.enumeration.BoneStatus;
/**
 * Test class for the BoneResource REST controller.
 *
 * @see BoneResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LabradorApp.class)
public class BoneResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final BoneStatus DEFAULT_STATUS = BoneStatus.BORROWED;
    private static final BoneStatus UPDATED_STATUS = BoneStatus.AVAILABLE;

    @Inject
    private BoneRepository boneRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restBoneMockMvc;

    private Bone bone;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BoneResource boneResource = new BoneResource();
        ReflectionTestUtils.setField(boneResource, "boneRepository", boneRepository);
        this.restBoneMockMvc = MockMvcBuilders.standaloneSetup(boneResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bone createEntity(EntityManager em) {
        Bone bone = new Bone()
                .name(DEFAULT_NAME)
                .description(DEFAULT_DESCRIPTION)
                .status(DEFAULT_STATUS);
        return bone;
    }

    @Before
    public void initTest() {
        bone = createEntity(em);
    }

    @Test
    @Transactional
    public void createBone() throws Exception {
        int databaseSizeBeforeCreate = boneRepository.findAll().size();

        // Create the Bone

        restBoneMockMvc.perform(post("/api/bones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bone)))
            .andExpect(status().isCreated());

        // Validate the Bone in the database
        List<Bone> boneList = boneRepository.findAll();
        assertThat(boneList).hasSize(databaseSizeBeforeCreate + 1);
        Bone testBone = boneList.get(boneList.size() - 1);
        assertThat(testBone.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBone.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBone.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createBoneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = boneRepository.findAll().size();

        // Create the Bone with an existing ID
        Bone existingBone = new Bone();
        existingBone.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBoneMockMvc.perform(post("/api/bones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingBone)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Bone> boneList = boneRepository.findAll();
        assertThat(boneList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBones() throws Exception {
        // Initialize the database
        boneRepository.saveAndFlush(bone);

        // Get all the boneList
        restBoneMockMvc.perform(get("/api/bones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bone.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getBone() throws Exception {
        // Initialize the database
        boneRepository.saveAndFlush(bone);

        // Get the bone
        restBoneMockMvc.perform(get("/api/bones/{id}", bone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bone.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBone() throws Exception {
        // Get the bone
        restBoneMockMvc.perform(get("/api/bones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBone() throws Exception {
        // Initialize the database
        boneRepository.saveAndFlush(bone);
        int databaseSizeBeforeUpdate = boneRepository.findAll().size();

        // Update the bone
        Bone updatedBone = boneRepository.findOne(bone.getId());
        updatedBone
                .name(UPDATED_NAME)
                .description(UPDATED_DESCRIPTION)
                .status(UPDATED_STATUS);

        restBoneMockMvc.perform(put("/api/bones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBone)))
            .andExpect(status().isOk());

        // Validate the Bone in the database
        List<Bone> boneList = boneRepository.findAll();
        assertThat(boneList).hasSize(databaseSizeBeforeUpdate);
        Bone testBone = boneList.get(boneList.size() - 1);
        assertThat(testBone.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBone.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBone.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingBone() throws Exception {
        int databaseSizeBeforeUpdate = boneRepository.findAll().size();

        // Create the Bone

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBoneMockMvc.perform(put("/api/bones")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bone)))
            .andExpect(status().isCreated());

        // Validate the Bone in the database
        List<Bone> boneList = boneRepository.findAll();
        assertThat(boneList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBone() throws Exception {
        // Initialize the database
        boneRepository.saveAndFlush(bone);
        int databaseSizeBeforeDelete = boneRepository.findAll().size();

        // Get the bone
        restBoneMockMvc.perform(delete("/api/bones/{id}", bone.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Bone> boneList = boneRepository.findAll();
        assertThat(boneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
