package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.*;
import it.vtsolutions.service.dto.CampioneBiologicoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CampioneBiologico and its DTO CampioneBiologicoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CampioneBiologicoMapper extends EntityMapper<CampioneBiologicoDTO, CampioneBiologico> {



    default CampioneBiologico fromId(Long id) {
        if (id == null) {
            return null;
        }
        CampioneBiologico campioneBiologico = new CampioneBiologico();
        campioneBiologico.setId(id);
        return campioneBiologico;
    }
}
