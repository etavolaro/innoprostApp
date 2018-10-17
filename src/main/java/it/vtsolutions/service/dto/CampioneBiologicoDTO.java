package it.vtsolutions.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import it.vtsolutions.domain.enumeration.TipoCampione;
import it.vtsolutions.domain.enumeration.TipoMalattia;
import it.vtsolutions.domain.enumeration.TipoRischio;

/**
 * A DTO for the CampioneBiologico entity.
 */
public class CampioneBiologicoDTO implements Serializable {

    private Long id;

    private String codRH;

    private Long numeroCartellaClinica;

    private String codUMG;

    private LocalDate dataReclutament;

    private Integer etaPaziente;

    private Integer dimensioneGhiandolaProstatica;

    private TipoCampione tipoCampione;

    private String valutazionePCA3;

    private Float psaTotale;

    private Float rapportoFT;

    private Float psaFree;

    private TipoMalattia malattia;

    private LocalDate dataBiopsia;

    private String esitoBiopsiaProstaticaGleasonScoreComposizione;

    private String esitoBiopsiaProstaticaGleasonScore;

    private Integer numeroPrelieviPositiviSulTotale;

    private Integer totalePrelievi;

    private Boolean pregressaChirurgiaProstatica;

    private Boolean terapiaInibitori5AlfaReduttasi;

    private Boolean terapiaAntiandrogenicaNeoadiuvante;

    private Boolean radioterapiaPelvi;

    private TipoRischio rischio;

    private LocalDate dataIntervento;

    private String esameIstologicoT;

    private String esameIstologicoN;

    private String esameIstologicoGleasonScoreComposizione;

    private Integer esameIstologicoGleasonScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodRH() {
        return codRH;
    }

    public void setCodRH(String codRH) {
        this.codRH = codRH;
    }

    public Long getNumeroCartellaClinica() {
        return numeroCartellaClinica;
    }

    public void setNumeroCartellaClinica(Long numeroCartellaClinica) {
        this.numeroCartellaClinica = numeroCartellaClinica;
    }

    public String getCodUMG() {
        return codUMG;
    }

    public void setCodUMG(String codUMG) {
        this.codUMG = codUMG;
    }

    public LocalDate getDataReclutament() {
        return dataReclutament;
    }

    public void setDataReclutament(LocalDate dataReclutament) {
        this.dataReclutament = dataReclutament;
    }

    public Integer getEtaPaziente() {
        return etaPaziente;
    }

    public void setEtaPaziente(Integer etaPaziente) {
        this.etaPaziente = etaPaziente;
    }

    public Integer getDimensioneGhiandolaProstatica() {
        return dimensioneGhiandolaProstatica;
    }

    public void setDimensioneGhiandolaProstatica(Integer dimensioneGhiandolaProstatica) {
        this.dimensioneGhiandolaProstatica = dimensioneGhiandolaProstatica;
    }

    public TipoCampione getTipoCampione() {
        return tipoCampione;
    }

    public void setTipoCampione(TipoCampione tipoCampione) {
        this.tipoCampione = tipoCampione;
    }

    public String getValutazionePCA3() {
        return valutazionePCA3;
    }

    public void setValutazionePCA3(String valutazionePCA3) {
        this.valutazionePCA3 = valutazionePCA3;
    }

    public Float getPsaTotale() {
        return psaTotale;
    }

    public void setPsaTotale(Float psaTotale) {
        this.psaTotale = psaTotale;
    }

    public Float getRapportoFT() {
        return rapportoFT;
    }

    public void setRapportoFT(Float rapportoFT) {
        this.rapportoFT = rapportoFT;
    }

    public Float getPsaFree() {
        return psaFree;
    }

    public void setPsaFree(Float psaFree) {
        this.psaFree = psaFree;
    }

    public TipoMalattia getMalattia() {
        return malattia;
    }

    public void setMalattia(TipoMalattia malattia) {
        this.malattia = malattia;
    }

    public LocalDate getDataBiopsia() {
        return dataBiopsia;
    }

    public void setDataBiopsia(LocalDate dataBiopsia) {
        this.dataBiopsia = dataBiopsia;
    }

    public String getEsitoBiopsiaProstaticaGleasonScoreComposizione() {
        return esitoBiopsiaProstaticaGleasonScoreComposizione;
    }

    public void setEsitoBiopsiaProstaticaGleasonScoreComposizione(String esitoBiopsiaProstaticaGleasonScoreComposizione) {
        this.esitoBiopsiaProstaticaGleasonScoreComposizione = esitoBiopsiaProstaticaGleasonScoreComposizione;
    }

    public String getEsitoBiopsiaProstaticaGleasonScore() {
        return esitoBiopsiaProstaticaGleasonScore;
    }

    public void setEsitoBiopsiaProstaticaGleasonScore(String esitoBiopsiaProstaticaGleasonScore) {
        this.esitoBiopsiaProstaticaGleasonScore = esitoBiopsiaProstaticaGleasonScore;
    }

    public Integer getNumeroPrelieviPositiviSulTotale() {
        return numeroPrelieviPositiviSulTotale;
    }

    public void setNumeroPrelieviPositiviSulTotale(Integer numeroPrelieviPositiviSulTotale) {
        this.numeroPrelieviPositiviSulTotale = numeroPrelieviPositiviSulTotale;
    }

    public Integer getTotalePrelievi() {
        return totalePrelievi;
    }

    public void setTotalePrelievi(Integer totalePrelievi) {
        this.totalePrelievi = totalePrelievi;
    }

    public Boolean isPregressaChirurgiaProstatica() {
        return pregressaChirurgiaProstatica;
    }

    public void setPregressaChirurgiaProstatica(Boolean pregressaChirurgiaProstatica) {
        this.pregressaChirurgiaProstatica = pregressaChirurgiaProstatica;
    }

    public Boolean isTerapiaInibitori5AlfaReduttasi() {
        return terapiaInibitori5AlfaReduttasi;
    }

    public void setTerapiaInibitori5AlfaReduttasi(Boolean terapiaInibitori5AlfaReduttasi) {
        this.terapiaInibitori5AlfaReduttasi = terapiaInibitori5AlfaReduttasi;
    }

    public Boolean isTerapiaAntiandrogenicaNeoadiuvante() {
        return terapiaAntiandrogenicaNeoadiuvante;
    }

    public void setTerapiaAntiandrogenicaNeoadiuvante(Boolean terapiaAntiandrogenicaNeoadiuvante) {
        this.terapiaAntiandrogenicaNeoadiuvante = terapiaAntiandrogenicaNeoadiuvante;
    }

    public Boolean isRadioterapiaPelvi() {
        return radioterapiaPelvi;
    }

    public void setRadioterapiaPelvi(Boolean radioterapiaPelvi) {
        this.radioterapiaPelvi = radioterapiaPelvi;
    }

    public TipoRischio getRischio() {
        return rischio;
    }

    public void setRischio(TipoRischio rischio) {
        this.rischio = rischio;
    }

    public LocalDate getDataIntervento() {
        return dataIntervento;
    }

    public void setDataIntervento(LocalDate dataIntervento) {
        this.dataIntervento = dataIntervento;
    }

    public String getEsameIstologicoT() {
        return esameIstologicoT;
    }

    public void setEsameIstologicoT(String esameIstologicoT) {
        this.esameIstologicoT = esameIstologicoT;
    }

    public String getEsameIstologicoN() {
        return esameIstologicoN;
    }

    public void setEsameIstologicoN(String esameIstologicoN) {
        this.esameIstologicoN = esameIstologicoN;
    }

    public String getEsameIstologicoGleasonScoreComposizione() {
        return esameIstologicoGleasonScoreComposizione;
    }

    public void setEsameIstologicoGleasonScoreComposizione(String esameIstologicoGleasonScoreComposizione) {
        this.esameIstologicoGleasonScoreComposizione = esameIstologicoGleasonScoreComposizione;
    }

    public Integer getEsameIstologicoGleasonScore() {
        return esameIstologicoGleasonScore;
    }

    public void setEsameIstologicoGleasonScore(Integer esameIstologicoGleasonScore) {
        this.esameIstologicoGleasonScore = esameIstologicoGleasonScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CampioneBiologicoDTO campioneBiologicoDTO = (CampioneBiologicoDTO) o;
        if (campioneBiologicoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campioneBiologicoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampioneBiologicoDTO{" +
            "id=" + getId() +
            ", codRH='" + getCodRH() + "'" +
            ", numeroCartellaClinica=" + getNumeroCartellaClinica() +
            ", codUMG='" + getCodUMG() + "'" +
            ", dataReclutament='" + getDataReclutament() + "'" +
            ", etaPaziente=" + getEtaPaziente() +
            ", dimensioneGhiandolaProstatica=" + getDimensioneGhiandolaProstatica() +
            ", tipoCampione='" + getTipoCampione() + "'" +
            ", valutazionePCA3='" + getValutazionePCA3() + "'" +
            ", psaTotale=" + getPsaTotale() +
            ", rapportoFT=" + getRapportoFT() +
            ", psaFree=" + getPsaFree() +
            ", malattia='" + getMalattia() + "'" +
            ", dataBiopsia='" + getDataBiopsia() + "'" +
            ", esitoBiopsiaProstaticaGleasonScoreComposizione='" + getEsitoBiopsiaProstaticaGleasonScoreComposizione() + "'" +
            ", esitoBiopsiaProstaticaGleasonScore='" + getEsitoBiopsiaProstaticaGleasonScore() + "'" +
            ", numeroPrelieviPositiviSulTotale=" + getNumeroPrelieviPositiviSulTotale() +
            ", totalePrelievi=" + getTotalePrelievi() +
            ", pregressaChirurgiaProstatica='" + isPregressaChirurgiaProstatica() + "'" +
            ", terapiaInibitori5AlfaReduttasi='" + isTerapiaInibitori5AlfaReduttasi() + "'" +
            ", terapiaAntiandrogenicaNeoadiuvante='" + isTerapiaAntiandrogenicaNeoadiuvante() + "'" +
            ", radioterapiaPelvi='" + isRadioterapiaPelvi() + "'" +
            ", rischio='" + getRischio() + "'" +
            ", dataIntervento='" + getDataIntervento() + "'" +
            ", esameIstologicoT='" + getEsameIstologicoT() + "'" +
            ", esameIstologicoN='" + getEsameIstologicoN() + "'" +
            ", esameIstologicoGleasonScoreComposizione='" + getEsameIstologicoGleasonScoreComposizione() + "'" +
            ", esameIstologicoGleasonScore=" + getEsameIstologicoGleasonScore() +
            "}";
    }
}
