package it.vtsolutions.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import it.vtsolutions.domain.enumeration.TipoCampione;

import it.vtsolutions.domain.enumeration.TipoMalattia;

import it.vtsolutions.domain.enumeration.TipoRischio;

/**
 * A CampioneBiologico.
 */
@Entity
@Table(name = "campione_biologico")
public class CampioneBiologico implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_rh")
    private String codRH;

    @Column(name = "numero_cartella_clinica")
    private Long numeroCartellaClinica;

    @Column(name = "cod_umg")
    private String codUMG;

    @Column(name = "data_esecuzione")
    private LocalDate dataEsecuzione;

    @Column(name = "eta_paziente")
    private Integer etaPaziente;

    @Column(name = "dimensione_ghiandola_prostatica")
    private Integer dimensioneGhiandolaProstatica;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_campione")
    private TipoCampione tipoCampione;

    @Column(name = "valutazione_pca_3")
    private String valutazionePCA3;

    @Column(name = "psa_totale")
    private Float psaTotale;

    @Column(name = "rapporto_ft")
    private Float rapportoFT;

    @Column(name = "psa_free")
    private Float psaFree;

    @Enumerated(EnumType.STRING)
    @Column(name = "malattia")
    private TipoMalattia malattia;

    @Column(name = "data_biopsia")
    private LocalDate dataBiopsia;

    @Column(name = "esito_biopsia_prostatica_gleason_score_composizione")
    private String esitoBiopsiaProstaticaGleasonScoreComposizione;

    @Column(name = "esito_biopsia_prostatica_gleason_score")
    private String esitoBiopsiaProstaticaGleasonScore;

    @Column(name = "numero_prelievi_positivi_sul_totale")
    private Integer numeroPrelieviPositiviSulTotale;

    @Column(name = "totale_prelievi")
    private Integer totalePrelievi;

    @Column(name = "pregressa_chirurgia_prostatica")
    private Boolean pregressaChirurgiaProstatica;

    @Column(name = "terapia_inibitori_5_alfa_reduttasi")
    private Boolean terapiaInibitori5AlfaReduttasi;

    @Column(name = "terapia_antiandrogenica_neoadiuvante")
    private Boolean terapiaAntiandrogenicaNeoadiuvante;

    @Column(name = "radioterapia_pelvi")
    private Boolean radioterapiaPelvi;

    @Enumerated(EnumType.STRING)
    @Column(name = "rischio")
    private TipoRischio rischio;

    @Column(name = "data_intervento")
    private LocalDate dataIntervento;

    @Column(name = "esame_istologico_t")
    private String esameIstologicoT;

    @Column(name = "esame_istologico_n")
    private String esameIstologicoN;

    @Column(name = "esame_istologico_gleason_score_composizione")
    private String esameIstologicoGleasonScoreComposizione;

    @Column(name = "esame_istologico_gleason_score")
    private Integer esameIstologicoGleasonScore;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodRH() {
        return codRH;
    }

    public CampioneBiologico codRH(String codRH) {
        this.codRH = codRH;
        return this;
    }

    public void setCodRH(String codRH) {
        this.codRH = codRH;
    }

    public Long getNumeroCartellaClinica() {
        return numeroCartellaClinica;
    }

    public CampioneBiologico numeroCartellaClinica(Long numeroCartellaClinica) {
        this.numeroCartellaClinica = numeroCartellaClinica;
        return this;
    }

    public void setNumeroCartellaClinica(Long numeroCartellaClinica) {
        this.numeroCartellaClinica = numeroCartellaClinica;
    }

    public String getCodUMG() {
        return codUMG;
    }

    public CampioneBiologico codUMG(String codUMG) {
        this.codUMG = codUMG;
        return this;
    }

    public void setCodUMG(String codUMG) {
        this.codUMG = codUMG;
    }

    public LocalDate getDataEsecuzione() {
        return dataEsecuzione;
    }

    public CampioneBiologico dataEsecuzione(LocalDate dataEsecuzione) {
        this.dataEsecuzione = dataEsecuzione;
        return this;
    }

    public void setDataEsecuzione(LocalDate dataEsecuzione) {
        this.dataEsecuzione = dataEsecuzione;
    }

    public Integer getEtaPaziente() {
        return etaPaziente;
    }

    public CampioneBiologico etaPaziente(Integer etaPaziente) {
        this.etaPaziente = etaPaziente;
        return this;
    }

    public void setEtaPaziente(Integer etaPaziente) {
        this.etaPaziente = etaPaziente;
    }

    public Integer getDimensioneGhiandolaProstatica() {
        return dimensioneGhiandolaProstatica;
    }

    public CampioneBiologico dimensioneGhiandolaProstatica(Integer dimensioneGhiandolaProstatica) {
        this.dimensioneGhiandolaProstatica = dimensioneGhiandolaProstatica;
        return this;
    }

    public void setDimensioneGhiandolaProstatica(Integer dimensioneGhiandolaProstatica) {
        this.dimensioneGhiandolaProstatica = dimensioneGhiandolaProstatica;
    }

    public TipoCampione getTipoCampione() {
        return tipoCampione;
    }

    public CampioneBiologico tipoCampione(TipoCampione tipoCampione) {
        this.tipoCampione = tipoCampione;
        return this;
    }

    public void setTipoCampione(TipoCampione tipoCampione) {
        this.tipoCampione = tipoCampione;
    }

    public String getValutazionePCA3() {
        return valutazionePCA3;
    }

    public CampioneBiologico valutazionePCA3(String valutazionePCA3) {
        this.valutazionePCA3 = valutazionePCA3;
        return this;
    }

    public void setValutazionePCA3(String valutazionePCA3) {
        this.valutazionePCA3 = valutazionePCA3;
    }

    public Float getPsaTotale() {
        return psaTotale;
    }

    public CampioneBiologico psaTotale(Float psaTotale) {
        this.psaTotale = psaTotale;
        return this;
    }

    public void setPsaTotale(Float psaTotale) {
        this.psaTotale = psaTotale;
    }

    public Float getRapportoFT() {
        return rapportoFT;
    }

    public CampioneBiologico rapportoFT(Float rapportoFT) {
        this.rapportoFT = rapportoFT;
        return this;
    }

    public void setRapportoFT(Float rapportoFT) {
        this.rapportoFT = rapportoFT;
    }

    public Float getPsaFree() {
        return psaFree;
    }

    public CampioneBiologico psaFree(Float psaFree) {
        this.psaFree = psaFree;
        return this;
    }

    public void setPsaFree(Float psaFree) {
        this.psaFree = psaFree;
    }

    public TipoMalattia getMalattia() {
        return malattia;
    }

    public CampioneBiologico malattia(TipoMalattia malattia) {
        this.malattia = malattia;
        return this;
    }

    public void setMalattia(TipoMalattia malattia) {
        this.malattia = malattia;
    }

    public LocalDate getDataBiopsia() {
        return dataBiopsia;
    }

    public CampioneBiologico dataBiopsia(LocalDate dataBiopsia) {
        this.dataBiopsia = dataBiopsia;
        return this;
    }

    public void setDataBiopsia(LocalDate dataBiopsia) {
        this.dataBiopsia = dataBiopsia;
    }

    public String getEsitoBiopsiaProstaticaGleasonScoreComposizione() {
        return esitoBiopsiaProstaticaGleasonScoreComposizione;
    }

    public CampioneBiologico esitoBiopsiaProstaticaGleasonScoreComposizione(String esitoBiopsiaProstaticaGleasonScoreComposizione) {
        this.esitoBiopsiaProstaticaGleasonScoreComposizione = esitoBiopsiaProstaticaGleasonScoreComposizione;
        return this;
    }

    public void setEsitoBiopsiaProstaticaGleasonScoreComposizione(String esitoBiopsiaProstaticaGleasonScoreComposizione) {
        this.esitoBiopsiaProstaticaGleasonScoreComposizione = esitoBiopsiaProstaticaGleasonScoreComposizione;
    }

    public String getEsitoBiopsiaProstaticaGleasonScore() {
        return esitoBiopsiaProstaticaGleasonScore;
    }

    public CampioneBiologico esitoBiopsiaProstaticaGleasonScore(String esitoBiopsiaProstaticaGleasonScore) {
        this.esitoBiopsiaProstaticaGleasonScore = esitoBiopsiaProstaticaGleasonScore;
        return this;
    }

    public void setEsitoBiopsiaProstaticaGleasonScore(String esitoBiopsiaProstaticaGleasonScore) {
        this.esitoBiopsiaProstaticaGleasonScore = esitoBiopsiaProstaticaGleasonScore;
    }

    public Integer getNumeroPrelieviPositiviSulTotale() {
        return numeroPrelieviPositiviSulTotale;
    }

    public CampioneBiologico numeroPrelieviPositiviSulTotale(Integer numeroPrelieviPositiviSulTotale) {
        this.numeroPrelieviPositiviSulTotale = numeroPrelieviPositiviSulTotale;
        return this;
    }

    public void setNumeroPrelieviPositiviSulTotale(Integer numeroPrelieviPositiviSulTotale) {
        this.numeroPrelieviPositiviSulTotale = numeroPrelieviPositiviSulTotale;
    }

    public Integer getTotalePrelievi() {
        return totalePrelievi;
    }

    public CampioneBiologico totalePrelievi(Integer totalePrelievi) {
        this.totalePrelievi = totalePrelievi;
        return this;
    }

    public void setTotalePrelievi(Integer totalePrelievi) {
        this.totalePrelievi = totalePrelievi;
    }

    public Boolean isPregressaChirurgiaProstatica() {
        return pregressaChirurgiaProstatica;
    }

    public CampioneBiologico pregressaChirurgiaProstatica(Boolean pregressaChirurgiaProstatica) {
        this.pregressaChirurgiaProstatica = pregressaChirurgiaProstatica;
        return this;
    }

    public void setPregressaChirurgiaProstatica(Boolean pregressaChirurgiaProstatica) {
        this.pregressaChirurgiaProstatica = pregressaChirurgiaProstatica;
    }

    public Boolean isTerapiaInibitori5AlfaReduttasi() {
        return terapiaInibitori5AlfaReduttasi;
    }

    public CampioneBiologico terapiaInibitori5AlfaReduttasi(Boolean terapiaInibitori5AlfaReduttasi) {
        this.terapiaInibitori5AlfaReduttasi = terapiaInibitori5AlfaReduttasi;
        return this;
    }

    public void setTerapiaInibitori5AlfaReduttasi(Boolean terapiaInibitori5AlfaReduttasi) {
        this.terapiaInibitori5AlfaReduttasi = terapiaInibitori5AlfaReduttasi;
    }

    public Boolean isTerapiaAntiandrogenicaNeoadiuvante() {
        return terapiaAntiandrogenicaNeoadiuvante;
    }

    public CampioneBiologico terapiaAntiandrogenicaNeoadiuvante(Boolean terapiaAntiandrogenicaNeoadiuvante) {
        this.terapiaAntiandrogenicaNeoadiuvante = terapiaAntiandrogenicaNeoadiuvante;
        return this;
    }

    public void setTerapiaAntiandrogenicaNeoadiuvante(Boolean terapiaAntiandrogenicaNeoadiuvante) {
        this.terapiaAntiandrogenicaNeoadiuvante = terapiaAntiandrogenicaNeoadiuvante;
    }

    public Boolean isRadioterapiaPelvi() {
        return radioterapiaPelvi;
    }

    public CampioneBiologico radioterapiaPelvi(Boolean radioterapiaPelvi) {
        this.radioterapiaPelvi = radioterapiaPelvi;
        return this;
    }

    public void setRadioterapiaPelvi(Boolean radioterapiaPelvi) {
        this.radioterapiaPelvi = radioterapiaPelvi;
    }

    public TipoRischio getRischio() {
        return rischio;
    }

    public CampioneBiologico rischio(TipoRischio rischio) {
        this.rischio = rischio;
        return this;
    }

    public void setRischio(TipoRischio rischio) {
        this.rischio = rischio;
    }

    public LocalDate getDataIntervento() {
        return dataIntervento;
    }

    public CampioneBiologico dataIntervento(LocalDate dataIntervento) {
        this.dataIntervento = dataIntervento;
        return this;
    }

    public void setDataIntervento(LocalDate dataIntervento) {
        this.dataIntervento = dataIntervento;
    }

    public String getEsameIstologicoT() {
        return esameIstologicoT;
    }

    public CampioneBiologico esameIstologicoT(String esameIstologicoT) {
        this.esameIstologicoT = esameIstologicoT;
        return this;
    }

    public void setEsameIstologicoT(String esameIstologicoT) {
        this.esameIstologicoT = esameIstologicoT;
    }

    public String getEsameIstologicoN() {
        return esameIstologicoN;
    }

    public CampioneBiologico esameIstologicoN(String esameIstologicoN) {
        this.esameIstologicoN = esameIstologicoN;
        return this;
    }

    public void setEsameIstologicoN(String esameIstologicoN) {
        this.esameIstologicoN = esameIstologicoN;
    }

    public String getEsameIstologicoGleasonScoreComposizione() {
        return esameIstologicoGleasonScoreComposizione;
    }

    public CampioneBiologico esameIstologicoGleasonScoreComposizione(String esameIstologicoGleasonScoreComposizione) {
        this.esameIstologicoGleasonScoreComposizione = esameIstologicoGleasonScoreComposizione;
        return this;
    }

    public void setEsameIstologicoGleasonScoreComposizione(String esameIstologicoGleasonScoreComposizione) {
        this.esameIstologicoGleasonScoreComposizione = esameIstologicoGleasonScoreComposizione;
    }

    public Integer getEsameIstologicoGleasonScore() {
        return esameIstologicoGleasonScore;
    }

    public CampioneBiologico esameIstologicoGleasonScore(Integer esameIstologicoGleasonScore) {
        this.esameIstologicoGleasonScore = esameIstologicoGleasonScore;
        return this;
    }

    public void setEsameIstologicoGleasonScore(Integer esameIstologicoGleasonScore) {
        this.esameIstologicoGleasonScore = esameIstologicoGleasonScore;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CampioneBiologico campioneBiologico = (CampioneBiologico) o;
        if (campioneBiologico.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campioneBiologico.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampioneBiologico{" +
            "id=" + getId() +
            ", codRH='" + getCodRH() + "'" +
            ", numeroCartellaClinica=" + getNumeroCartellaClinica() +
            ", codUMG='" + getCodUMG() + "'" +
            ", dataEsecuzione='" + getDataEsecuzione() + "'" +
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
