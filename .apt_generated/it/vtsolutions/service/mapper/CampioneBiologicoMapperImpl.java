package it.vtsolutions.service.mapper;

import it.vtsolutions.domain.CampioneBiologico;
import it.vtsolutions.service.dto.CampioneBiologicoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-07-09T13:06:37+0200",
    comments = "version: 1.2.0.Final, compiler: Eclipse JDT (IDE) 3.13.102.v20180330-0919, environment: Java 1.8.0_101 (Oracle Corporation)"
)
@Component
public class CampioneBiologicoMapperImpl implements CampioneBiologicoMapper {

    @Override
    public CampioneBiologico toEntity(CampioneBiologicoDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CampioneBiologico campioneBiologico = new CampioneBiologico();

        campioneBiologico.setId( dto.getId() );
        campioneBiologico.setCodRH( dto.getCodRH() );
        campioneBiologico.setNumeroCartellaClinica( dto.getNumeroCartellaClinica() );
        campioneBiologico.setCodUMG( dto.getCodUMG() );
        campioneBiologico.setDataEsecuzione( dto.getDataEsecuzione() );
        campioneBiologico.setEtaPaziente( dto.getEtaPaziente() );
        campioneBiologico.setDimensioneGhiandolaProstatica( dto.getDimensioneGhiandolaProstatica() );
        campioneBiologico.setTipoCampione( dto.getTipoCampione() );
        campioneBiologico.setValutazionePCA3( dto.getValutazionePCA3() );
        campioneBiologico.setPsaTotale( dto.getPsaTotale() );
        campioneBiologico.setRapportoFT( dto.getRapportoFT() );
        campioneBiologico.setPsaFree( dto.getPsaFree() );
        campioneBiologico.setMalattia( dto.getMalattia() );
        campioneBiologico.setDataBiopsia( dto.getDataBiopsia() );
        campioneBiologico.setEsitoBiopsiaProstaticaGleasonScoreComposizione( dto.getEsitoBiopsiaProstaticaGleasonScoreComposizione() );
        campioneBiologico.setEsitoBiopsiaProstaticaGleasonScore( dto.getEsitoBiopsiaProstaticaGleasonScore() );
        campioneBiologico.setNumeroPrelieviPositiviSulTotale( dto.getNumeroPrelieviPositiviSulTotale() );
        campioneBiologico.setTotalePrelievi( dto.getTotalePrelievi() );
        campioneBiologico.setPregressaChirurgiaProstatica( dto.isPregressaChirurgiaProstatica() );
        campioneBiologico.setTerapiaInibitori5AlfaReduttasi( dto.isTerapiaInibitori5AlfaReduttasi() );
        campioneBiologico.setTerapiaAntiandrogenicaNeoadiuvante( dto.isTerapiaAntiandrogenicaNeoadiuvante() );
        campioneBiologico.setRadioterapiaPelvi( dto.isRadioterapiaPelvi() );
        campioneBiologico.setRischio( dto.getRischio() );
        campioneBiologico.setDataIntervento( dto.getDataIntervento() );
        campioneBiologico.setEsameIstologicoT( dto.getEsameIstologicoT() );
        campioneBiologico.setEsameIstologicoN( dto.getEsameIstologicoN() );
        campioneBiologico.setEsameIstologicoGleasonScoreComposizione( dto.getEsameIstologicoGleasonScoreComposizione() );
        campioneBiologico.setEsameIstologicoGleasonScore( dto.getEsameIstologicoGleasonScore() );

        return campioneBiologico;
    }

    @Override
    public CampioneBiologicoDTO toDto(CampioneBiologico entity) {
        if ( entity == null ) {
            return null;
        }

        CampioneBiologicoDTO campioneBiologicoDTO = new CampioneBiologicoDTO();

        campioneBiologicoDTO.setId( entity.getId() );
        campioneBiologicoDTO.setCodRH( entity.getCodRH() );
        campioneBiologicoDTO.setNumeroCartellaClinica( entity.getNumeroCartellaClinica() );
        campioneBiologicoDTO.setCodUMG( entity.getCodUMG() );
        campioneBiologicoDTO.setDataEsecuzione( entity.getDataEsecuzione() );
        campioneBiologicoDTO.setEtaPaziente( entity.getEtaPaziente() );
        campioneBiologicoDTO.setDimensioneGhiandolaProstatica( entity.getDimensioneGhiandolaProstatica() );
        campioneBiologicoDTO.setTipoCampione( entity.getTipoCampione() );
        campioneBiologicoDTO.setValutazionePCA3( entity.getValutazionePCA3() );
        campioneBiologicoDTO.setPsaTotale( entity.getPsaTotale() );
        campioneBiologicoDTO.setRapportoFT( entity.getRapportoFT() );
        campioneBiologicoDTO.setPsaFree( entity.getPsaFree() );
        campioneBiologicoDTO.setMalattia( entity.getMalattia() );
        campioneBiologicoDTO.setDataBiopsia( entity.getDataBiopsia() );
        campioneBiologicoDTO.setEsitoBiopsiaProstaticaGleasonScoreComposizione( entity.getEsitoBiopsiaProstaticaGleasonScoreComposizione() );
        campioneBiologicoDTO.setEsitoBiopsiaProstaticaGleasonScore( entity.getEsitoBiopsiaProstaticaGleasonScore() );
        campioneBiologicoDTO.setNumeroPrelieviPositiviSulTotale( entity.getNumeroPrelieviPositiviSulTotale() );
        campioneBiologicoDTO.setTotalePrelievi( entity.getTotalePrelievi() );
        campioneBiologicoDTO.setPregressaChirurgiaProstatica( entity.isPregressaChirurgiaProstatica() );
        campioneBiologicoDTO.setTerapiaInibitori5AlfaReduttasi( entity.isTerapiaInibitori5AlfaReduttasi() );
        campioneBiologicoDTO.setTerapiaAntiandrogenicaNeoadiuvante( entity.isTerapiaAntiandrogenicaNeoadiuvante() );
        campioneBiologicoDTO.setRadioterapiaPelvi( entity.isRadioterapiaPelvi() );
        campioneBiologicoDTO.setRischio( entity.getRischio() );
        campioneBiologicoDTO.setDataIntervento( entity.getDataIntervento() );
        campioneBiologicoDTO.setEsameIstologicoT( entity.getEsameIstologicoT() );
        campioneBiologicoDTO.setEsameIstologicoN( entity.getEsameIstologicoN() );
        campioneBiologicoDTO.setEsameIstologicoGleasonScoreComposizione( entity.getEsameIstologicoGleasonScoreComposizione() );
        campioneBiologicoDTO.setEsameIstologicoGleasonScore( entity.getEsameIstologicoGleasonScore() );

        return campioneBiologicoDTO;
    }

    @Override
    public List<CampioneBiologico> toEntity(List<CampioneBiologicoDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<CampioneBiologico> list = new ArrayList<CampioneBiologico>( dtoList.size() );
        for ( CampioneBiologicoDTO campioneBiologicoDTO : dtoList ) {
            list.add( toEntity( campioneBiologicoDTO ) );
        }

        return list;
    }

    @Override
    public List<CampioneBiologicoDTO> toDto(List<CampioneBiologico> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CampioneBiologicoDTO> list = new ArrayList<CampioneBiologicoDTO>( entityList.size() );
        for ( CampioneBiologico campioneBiologico : entityList ) {
            list.add( toDto( campioneBiologico ) );
        }

        return list;
    }
}
