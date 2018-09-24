package it.vtsolutions.service.impl;

import it.vtsolutions.service.CampioneBiologicoService;
import it.vtsolutions.domain.CampioneBiologico;
import it.vtsolutions.repository.CampioneBiologicoRepository;
import it.vtsolutions.service.dto.CampioneBiologicoDTO;
import it.vtsolutions.service.mapper.CampioneBiologicoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing CampioneBiologico.
 */
@Service
@Transactional
public class CampioneBiologicoServiceImpl implements CampioneBiologicoService {

    private final Logger log = LoggerFactory.getLogger(CampioneBiologicoServiceImpl.class);

    private final CampioneBiologicoRepository campioneBiologicoRepository;

    private final CampioneBiologicoMapper campioneBiologicoMapper;

    public CampioneBiologicoServiceImpl(CampioneBiologicoRepository campioneBiologicoRepository, CampioneBiologicoMapper campioneBiologicoMapper) {
        this.campioneBiologicoRepository = campioneBiologicoRepository;
        this.campioneBiologicoMapper = campioneBiologicoMapper;
    }

    /**
     * Save a campioneBiologico.
     *
     * @param campioneBiologicoDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CampioneBiologicoDTO save(CampioneBiologicoDTO campioneBiologicoDTO) {
        log.debug("Request to save CampioneBiologico : {}", campioneBiologicoDTO);
        CampioneBiologico campioneBiologico = campioneBiologicoMapper.toEntity(campioneBiologicoDTO);
        campioneBiologico = campioneBiologicoRepository.save(campioneBiologico);
        return campioneBiologicoMapper.toDto(campioneBiologico);
    }

    /**
     * Get all the campioneBiologicos.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CampioneBiologicoDTO> findAll() {
        log.debug("Request to get all CampioneBiologicos");
        return campioneBiologicoRepository.findAll().stream()
            .map(campioneBiologicoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one campioneBiologico by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CampioneBiologicoDTO> findOne(Long id) {
        log.debug("Request to get CampioneBiologico : {}", id);
        return campioneBiologicoRepository.findById(id)
            .map(campioneBiologicoMapper::toDto);
    }

    /**
     * Delete the campioneBiologico by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CampioneBiologico : {}", id);
        campioneBiologicoRepository.deleteById(id);
    }
}
