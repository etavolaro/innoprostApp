package it.vtsolutions.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.vtsolutions.service.CampioneBiologicoService;
import it.vtsolutions.web.rest.errors.BadRequestAlertException;
import it.vtsolutions.web.rest.util.HeaderUtil;
import it.vtsolutions.web.rest.util.PaginationUtil;
import it.vtsolutions.service.dto.CampioneBiologicoDTO;
import it.vtsolutions.service.dto.CampioneBiologicoCriteria;
import it.vtsolutions.service.CampioneBiologicoQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CampioneBiologico.
 */
@RestController
@RequestMapping("/api")
public class CampioneBiologicoResource {

    private final Logger log = LoggerFactory.getLogger(CampioneBiologicoResource.class);

    private static final String ENTITY_NAME = "campioneBiologico";

    private final CampioneBiologicoService campioneBiologicoService;

    private final CampioneBiologicoQueryService campioneBiologicoQueryService;

    public CampioneBiologicoResource(CampioneBiologicoService campioneBiologicoService, CampioneBiologicoQueryService campioneBiologicoQueryService) {
        this.campioneBiologicoService = campioneBiologicoService;
        this.campioneBiologicoQueryService = campioneBiologicoQueryService;
    }

    /**
     * POST  /campione-biologicos : Create a new campioneBiologico.
     *
     * @param campioneBiologicoDTO the campioneBiologicoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new campioneBiologicoDTO, or with status 400 (Bad Request) if the campioneBiologico has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/campione-biologicos")
    @Timed
    public ResponseEntity<CampioneBiologicoDTO> createCampioneBiologico(@RequestBody CampioneBiologicoDTO campioneBiologicoDTO) throws URISyntaxException {
        log.debug("REST request to save CampioneBiologico : {}", campioneBiologicoDTO);
        if (campioneBiologicoDTO.getId() != null) {
            throw new BadRequestAlertException("A new campioneBiologico cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampioneBiologicoDTO result = campioneBiologicoService.save(campioneBiologicoDTO);
        return ResponseEntity.created(new URI("/api/campione-biologicos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /campione-biologicos : Updates an existing campioneBiologico.
     *
     * @param campioneBiologicoDTO the campioneBiologicoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated campioneBiologicoDTO,
     * or with status 400 (Bad Request) if the campioneBiologicoDTO is not valid,
     * or with status 500 (Internal Server Error) if the campioneBiologicoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/campione-biologicos")
    @Timed
    public ResponseEntity<CampioneBiologicoDTO> updateCampioneBiologico(@RequestBody CampioneBiologicoDTO campioneBiologicoDTO) throws URISyntaxException {
        log.debug("REST request to update CampioneBiologico : {}", campioneBiologicoDTO);
        if (campioneBiologicoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampioneBiologicoDTO result = campioneBiologicoService.save(campioneBiologicoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, campioneBiologicoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /campione-biologicos : get all the campioneBiologicos.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of campioneBiologicos in body
     */
    @GetMapping("/campione-biologicos")
    @Timed
    public ResponseEntity<List<CampioneBiologicoDTO>> getAllCampioneBiologicos(CampioneBiologicoCriteria criteria, Pageable pageable) {
        log.debug("REST request to get CampioneBiologicos by criteria: {}", criteria);
        Page<CampioneBiologicoDTO> page = campioneBiologicoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/campione-biologicos");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /campione-biologicos/:id : get the "id" campioneBiologico.
     *
     * @param id the id of the campioneBiologicoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the campioneBiologicoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/campione-biologicos/{id}")
    @Timed
    public ResponseEntity<CampioneBiologicoDTO> getCampioneBiologico(@PathVariable Long id) {
        log.debug("REST request to get CampioneBiologico : {}", id);
        Optional<CampioneBiologicoDTO> campioneBiologicoDTO = campioneBiologicoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campioneBiologicoDTO);
    }

    /**
     * DELETE  /campione-biologicos/:id : delete the "id" campioneBiologico.
     *
     * @param id the id of the campioneBiologicoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/campione-biologicos/{id}")
    @Timed
    public ResponseEntity<Void> deleteCampioneBiologico(@PathVariable Long id) {
        log.debug("REST request to delete CampioneBiologico : {}", id);
        campioneBiologicoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
