package br.com.labrador.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.labrador.domain.Loans;

import br.com.labrador.repository.LoansRepository;
import br.com.labrador.web.rest.util.HeaderUtil;
import br.com.labrador.web.rest.util.PaginationUtil;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Loans.
 */
@RestController
@RequestMapping("/api")
public class LoansResource {

    private final Logger log = LoggerFactory.getLogger(LoansResource.class);
        
    @Inject
    private LoansRepository loansRepository;

    /**
     * POST  /loans : Create a new loans.
     *
     * @param loans the loans to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loans, or with status 400 (Bad Request) if the loans has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loans")
    @Timed
    public ResponseEntity<Loans> createLoans(@RequestBody Loans loans) throws URISyntaxException {
        log.debug("REST request to save Loans : {}", loans);
        if (loans.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("loans", "idexists", "A new loans cannot already have an ID")).body(null);
        }
        Loans result = loansRepository.save(loans);
        return ResponseEntity.created(new URI("/api/loans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("loans", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loans : Updates an existing loans.
     *
     * @param loans the loans to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loans,
     * or with status 400 (Bad Request) if the loans is not valid,
     * or with status 500 (Internal Server Error) if the loans couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loans")
    @Timed
    public ResponseEntity<Loans> updateLoans(@RequestBody Loans loans) throws URISyntaxException {
        log.debug("REST request to update Loans : {}", loans);
        if (loans.getId() == null) {
            return createLoans(loans);
        }
        Loans result = loansRepository.save(loans);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("loans", loans.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loans : get all the loans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of loans in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/loans")
    @Timed
    public ResponseEntity<List<Loans>> getAllLoans(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Loans");
        Page<Loans> page = loansRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/loans");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /loans/:id : get the "id" loans.
     *
     * @param id the id of the loans to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loans, or with status 404 (Not Found)
     */
    @GetMapping("/loans/{id}")
    @Timed
    public ResponseEntity<Loans> getLoans(@PathVariable Long id) {
        log.debug("REST request to get Loans : {}", id);
        Loans loans = loansRepository.findOne(id);
        return Optional.ofNullable(loans)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /loans/:id : delete the "id" loans.
     *
     * @param id the id of the loans to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loans/{id}")
    @Timed
    public ResponseEntity<Void> deleteLoans(@PathVariable Long id) {
        log.debug("REST request to delete Loans : {}", id);
        loansRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("loans", id.toString())).build();
    }

}
