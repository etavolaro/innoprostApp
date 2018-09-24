package it.vtsolutions.service;

import it.vtsolutions.service.dto.CampioneBiologicoDTO;

import java.util.List;
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
     * @return the list of entities
     */
    List<CampioneBiologicoDTO> findAll();


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
