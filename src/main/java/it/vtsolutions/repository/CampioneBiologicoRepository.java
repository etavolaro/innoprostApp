package it.vtsolutions.repository;

import it.vtsolutions.domain.CampioneBiologico;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CampioneBiologico entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampioneBiologicoRepository extends JpaRepository<CampioneBiologico, Long> {

}
