package br.com.labrador.web.rest;

import br.com.labrador.LabradorApp;

import br.com.labrador.domain.Loans;
import br.com.labrador.repository.LoansRepository;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static br.com.labrador.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the LoansResource REST controller.
 *
 * @see LoansResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LabradorApp.class)
public class LoansResourceIntTest {

    private static final ZonedDateTime DEFAULT_LOAN_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LOAN_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_RETURN_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_RETURN_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    @Inject
    private LoansRepository loansRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restLoansMockMvc;

    private Loans loans;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LoansResource loansResource = new LoansResource();
        ReflectionTestUtils.setField(loansResource, "loansRepository", loansRepository);
        this.restLoansMockMvc = MockMvcBuilders.standaloneSetup(loansResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Loans createEntity(EntityManager em) {
        Loans loans = new Loans()
                .loanDate(DEFAULT_LOAN_DATE)
                .returnDate(DEFAULT_RETURN_DATE);
        return loans;
    }

    @Before
    public void initTest() {
        loans = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoans() throws Exception {
        int databaseSizeBeforeCreate = loansRepository.findAll().size();

        // Create the Loans

        restLoansMockMvc.perform(post("/api/loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loans)))
            .andExpect(status().isCreated());

        // Validate the Loans in the database
        List<Loans> loansList = loansRepository.findAll();
        assertThat(loansList).hasSize(databaseSizeBeforeCreate + 1);
        Loans testLoans = loansList.get(loansList.size() - 1);
        assertThat(testLoans.getLoanDate()).isEqualTo(DEFAULT_LOAN_DATE);
        assertThat(testLoans.getReturnDate()).isEqualTo(DEFAULT_RETURN_DATE);
    }

    @Test
    @Transactional
    public void createLoansWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = loansRepository.findAll().size();

        // Create the Loans with an existing ID
        Loans existingLoans = new Loans();
        existingLoans.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLoansMockMvc.perform(post("/api/loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingLoans)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Loans> loansList = loansRepository.findAll();
        assertThat(loansList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLoans() throws Exception {
        // Initialize the database
        loansRepository.saveAndFlush(loans);

        // Get all the loansList
        restLoansMockMvc.perform(get("/api/loans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loans.getId().intValue())))
            .andExpect(jsonPath("$.[*].loanDate").value(hasItem(sameInstant(DEFAULT_LOAN_DATE))))
            .andExpect(jsonPath("$.[*].returnDate").value(hasItem(sameInstant(DEFAULT_RETURN_DATE))));
    }

    @Test
    @Transactional
    public void getLoans() throws Exception {
        // Initialize the database
        loansRepository.saveAndFlush(loans);

        // Get the loans
        restLoansMockMvc.perform(get("/api/loans/{id}", loans.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loans.getId().intValue()))
            .andExpect(jsonPath("$.loanDate").value(sameInstant(DEFAULT_LOAN_DATE)))
            .andExpect(jsonPath("$.returnDate").value(sameInstant(DEFAULT_RETURN_DATE)));
    }

    @Test
    @Transactional
    public void getNonExistingLoans() throws Exception {
        // Get the loans
        restLoansMockMvc.perform(get("/api/loans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoans() throws Exception {
        // Initialize the database
        loansRepository.saveAndFlush(loans);
        int databaseSizeBeforeUpdate = loansRepository.findAll().size();

        // Update the loans
        Loans updatedLoans = loansRepository.findOne(loans.getId());
        updatedLoans
                .loanDate(UPDATED_LOAN_DATE)
                .returnDate(UPDATED_RETURN_DATE);

        restLoansMockMvc.perform(put("/api/loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLoans)))
            .andExpect(status().isOk());

        // Validate the Loans in the database
        List<Loans> loansList = loansRepository.findAll();
        assertThat(loansList).hasSize(databaseSizeBeforeUpdate);
        Loans testLoans = loansList.get(loansList.size() - 1);
        assertThat(testLoans.getLoanDate()).isEqualTo(UPDATED_LOAN_DATE);
        assertThat(testLoans.getReturnDate()).isEqualTo(UPDATED_RETURN_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingLoans() throws Exception {
        int databaseSizeBeforeUpdate = loansRepository.findAll().size();

        // Create the Loans

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLoansMockMvc.perform(put("/api/loans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(loans)))
            .andExpect(status().isCreated());

        // Validate the Loans in the database
        List<Loans> loansList = loansRepository.findAll();
        assertThat(loansList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLoans() throws Exception {
        // Initialize the database
        loansRepository.saveAndFlush(loans);
        int databaseSizeBeforeDelete = loansRepository.findAll().size();

        // Get the loans
        restLoansMockMvc.perform(delete("/api/loans/{id}", loans.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Loans> loansList = loansRepository.findAll();
        assertThat(loansList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
