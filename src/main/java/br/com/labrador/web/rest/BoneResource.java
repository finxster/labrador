package br.com.labrador.web.rest;

import com.codahale.metrics.annotation.Timed;
import br.com.labrador.domain.Bone;

import br.com.labrador.repository.BoneRepository;
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
 * REST controller for managing Bone.
 */
@RestController
@RequestMapping("/api")
public class BoneResource {

    private final Logger log = LoggerFactory.getLogger(BoneResource.class);
        
    @Inject
    private BoneRepository boneRepository;

    /**
     * POST  /bones : Create a new bone.
     *
     * @param bone the bone to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bone, or with status 400 (Bad Request) if the bone has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bones")
    @Timed
    public ResponseEntity<Bone> createBone(@RequestBody Bone bone) throws URISyntaxException {
        log.debug("REST request to save Bone : {}", bone);
        if (bone.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("bone", "idexists", "A new bone cannot already have an ID")).body(null);
        }
        Bone result = boneRepository.save(bone);
        return ResponseEntity.created(new URI("/api/bones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("bone", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bones : Updates an existing bone.
     *
     * @param bone the bone to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bone,
     * or with status 400 (Bad Request) if the bone is not valid,
     * or with status 500 (Internal Server Error) if the bone couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bones")
    @Timed
    public ResponseEntity<Bone> updateBone(@RequestBody Bone bone) throws URISyntaxException {
        log.debug("REST request to update Bone : {}", bone);
        if (bone.getId() == null) {
            return createBone(bone);
        }
        Bone result = boneRepository.save(bone);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("bone", bone.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bones : get all the bones.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bones in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/bones")
    @Timed
    public ResponseEntity<List<Bone>> getAllBones(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Bones");
        Page<Bone> page = boneRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bones");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /bones/:id : get the "id" bone.
     *
     * @param id the id of the bone to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bone, or with status 404 (Not Found)
     */
    @GetMapping("/bones/{id}")
    @Timed
    public ResponseEntity<Bone> getBone(@PathVariable Long id) {
        log.debug("REST request to get Bone : {}", id);
        Bone bone = boneRepository.findOne(id);
        return Optional.ofNullable(bone)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /bones/:id : delete the "id" bone.
     *
     * @param id the id of the bone to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bones/{id}")
    @Timed
    public ResponseEntity<Void> deleteBone(@PathVariable Long id) {
        log.debug("REST request to delete Bone : {}", id);
        boneRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("bone", id.toString())).build();
    }

}
