package it.vtsolutions.service;

import it.vtsolutions.service.dto.CampioneBiologicoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing CampioneBiologico.
 */
public interface CampioneBiologicoService {

    /**
     * Save a campioneBiologico.
     *
     * @param campioneBiologicoDTO the entity to save
     * @return the persisted entity
     */
    CampioneBiologicoDTO save(CampioneBiologicoDTO campioneBiologicoDTO);

    /**
     * Get all the campioneBiologicos.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CampioneBiologicoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" campioneBiologico.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CampioneBiologicoDTO> findOne(Long id);

    /**
     * Delete the "id" campioneBiologico.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
