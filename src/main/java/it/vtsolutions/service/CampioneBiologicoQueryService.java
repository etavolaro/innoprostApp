package it.vtsolutions.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import it.vtsolutions.domain.CampioneBiologico;
import it.vtsolutions.domain.*; // for static metamodels
import it.vtsolutions.repository.CampioneBiologicoRepository;
import it.vtsolutions.service.dto.CampioneBiologicoCriteria;

import it.vtsolutions.service.dto.CampioneBiologicoDTO;
import it.vtsolutions.service.mapper.CampioneBiologicoMapper;

/**
 * Service for executing complex queries for CampioneBiologico entities in the database.
 * The main input is a {@link CampioneBiologicoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CampioneBiologicoDTO} or a {@link Page} of {@link CampioneBiologicoDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CampioneBiologicoQueryService extends QueryService<CampioneBiologico> {

    private final Logger log = LoggerFactory.getLogger(CampioneBiologicoQueryService.class);

    private final CampioneBiologicoRepository campioneBiologicoRepository;

    private final CampioneBiologicoMapper campioneBiologicoMapper;

    public CampioneBiologicoQueryService(CampioneBiologicoRepository campioneBiologicoRepository, CampioneBiologicoMapper campioneBiologicoMapper) {
        this.campioneBiologicoRepository = campioneBiologicoRepository;
        this.campioneBiologicoMapper = campioneBiologicoMapper;
    }

    /**
     * Return a {@link List} of {@link CampioneBiologicoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CampioneBiologicoDTO> findByCriteria(CampioneBiologicoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CampioneBiologico> specification = createSpecification(criteria);
        return campioneBiologicoMapper.toDto(campioneBiologicoRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CampioneBiologicoDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CampioneBiologicoDTO> findByCriteria(CampioneBiologicoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CampioneBiologico> specification = createSpecification(criteria);
        return campioneBiologicoRepository.findAll(specification, page)
            .map(campioneBiologicoMapper::toDto);
    }

    /**
     * Function to convert CampioneBiologicoCriteria to a {@link Specification}
     */
    private Specification<CampioneBiologico> createSpecification(CampioneBiologicoCriteria criteria) {
        Specification<CampioneBiologico> specification = Specification.where(null);
        if (criteria != null) {
          /*  if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CampioneBiologico_.id));
            }*/
            if (criteria.getCodRH() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodRH(), CampioneBiologico_.codRH));
            } 
            if (criteria.getNumeroCartellaClinica() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumeroCartellaClinica(), CampioneBiologico_.numeroCartellaClinica));
            }
      /*      if (criteria.getCodUMG() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodUMG(), CampioneBiologico_.codUMG));
            }
            if (criteria.getDataReclutament() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataReclutament(), CampioneBiologico_.dataReclutament));
            }
            if (criteria.getEtaPaziente() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEtaPaziente(), CampioneBiologico_.etaPaziente));
            }
            if (criteria.getDimensioneGhiandolaProstatica() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDimensioneGhiandolaProstatica(), CampioneBiologico_.dimensioneGhiandolaProstatica));
            }*/
            if (criteria.getTipoCampione() != null) {
                specification = specification.and(buildSpecification(criteria.getTipoCampione(), CampioneBiologico_.tipoCampione));
            }
         /*   if (criteria.getValutazionePCA3() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValutazionePCA3(), CampioneBiologico_.valutazionePCA3));
            }
            if (criteria.getPsaTotale() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPsaTotale(), CampioneBiologico_.psaTotale));
            }
            if (criteria.getRapportoFT() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRapportoFT(), CampioneBiologico_.rapportoFT));
            }
            if (criteria.getPsaFree() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPsaFree(), CampioneBiologico_.psaFree));
            }*/
            if (criteria.getMalattia() != null) {
                specification = specification.and(buildSpecification(criteria.getMalattia(), CampioneBiologico_.malattia));
            }
      /*      if (criteria.getDataBiopsia() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataBiopsia(), CampioneBiologico_.dataBiopsia));
            }
            if (criteria.getEsitoBiopsiaProstaticaGleasonScoreComposizione() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsitoBiopsiaProstaticaGleasonScoreComposizione(), CampioneBiologico_.esitoBiopsiaProstaticaGleasonScoreComposizione));
            }
            if (criteria.getEsitoBiopsiaProstaticaGleasonScore() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsitoBiopsiaProstaticaGleasonScore(), CampioneBiologico_.esitoBiopsiaProstaticaGleasonScore));
            }
            if (criteria.getNumeroPrelieviPositiviSulTotale() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumeroPrelieviPositiviSulTotale(), CampioneBiologico_.numeroPrelieviPositiviSulTotale));
            }
            if (criteria.getTotalePrelievi() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalePrelievi(), CampioneBiologico_.totalePrelievi));
            }*/
            if (criteria.getPregressaChirurgiaProstatica() != null) {
                specification = specification.and(buildSpecification(criteria.getPregressaChirurgiaProstatica(), CampioneBiologico_.pregressaChirurgiaProstatica));
            }
           /* if (criteria.getTerapiaInibitori5AlfaReduttasi() != null) {
                specification = specification.and(buildSpecification(criteria.getTerapiaInibitori5AlfaReduttasi(), CampioneBiologico_.terapiaInibitori5AlfaReduttasi));
            }
            if (criteria.getTerapiaAntiandrogenicaNeoadiuvante() != null) {
                specification = specification.and(buildSpecification(criteria.getTerapiaAntiandrogenicaNeoadiuvante(), CampioneBiologico_.terapiaAntiandrogenicaNeoadiuvante));
            }
            if (criteria.getRadioterapiaPelvi() != null) {
                specification = specification.and(buildSpecification(criteria.getRadioterapiaPelvi(), CampioneBiologico_.radioterapiaPelvi));
            }
            if (criteria.getRischio() != null) {
                specification = specification.and(buildSpecification(criteria.getRischio(), CampioneBiologico_.rischio));
            }
            if (criteria.getDataIntervento() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDataIntervento(), CampioneBiologico_.dataIntervento));
            }
            if (criteria.getEsameIstologicoT() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsameIstologicoT(), CampioneBiologico_.esameIstologicoT));
            }
            if (criteria.getEsameIstologicoN() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsameIstologicoN(), CampioneBiologico_.esameIstologicoN));
            }
            if (criteria.getEsameIstologicoGleasonScoreComposizione() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEsameIstologicoGleasonScoreComposizione(), CampioneBiologico_.esameIstologicoGleasonScoreComposizione));
            }
            if (criteria.getEsameIstologicoGleasonScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEsameIstologicoGleasonScore(), CampioneBiologico_.esameIstologicoGleasonScore));
            }*/
        }
        return specification;
    }

}
