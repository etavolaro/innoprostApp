package it.vtsolutions.service.dto;

import java.io.Serializable;
import it.vtsolutions.domain.enumeration.TipoCampione;
import it.vtsolutions.domain.enumeration.TipoMalattia;
import it.vtsolutions.domain.enumeration.TipoRischio;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;


import io.github.jhipster.service.filter.LocalDateFilter;



/**
 * Criteria class for the CampioneBiologico entity. This class is used in CampioneBiologicoResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /campione-biologicos?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CampioneBiologicoCriteria implements Serializable {
    /**
     * Class for filtering TipoCampione
     */
    public static class TipoCampioneFilter extends Filter<TipoCampione> {
    }

    /**
     * Class for filtering TipoMalattia
     */
    public static class TipoMalattiaFilter extends Filter<TipoMalattia> {
    }

    /**
     * Class for filtering TipoRischio
     */
    public static class TipoRischioFilter extends Filter<TipoRischio> {
    }

    private static final long serialVersionUID = 1L;


   // private LongFilter id;

    private StringFilter codRH;

    private LongFilter numeroCartellaClinica;

    private StringFilter codUMG;

  /*  private LocalDateFilter dataReclutament;

    private IntegerFilter etaPaziente;

    private IntegerFilter dimensioneGhiandolaProstatica; */

    private TipoCampioneFilter tipoCampione;

  /*  private StringFilter valutazionePCA3;

    private FloatFilter psaTotale;

    private FloatFilter rapportoFT;

    private FloatFilter psaFree;*/

    private TipoMalattiaFilter malattia;

  /*  private LocalDateFilter dataBiopsia;

    private StringFilter esitoBiopsiaProstaticaGleasonScoreComposizione;

    private StringFilter esitoBiopsiaProstaticaGleasonScore;

    private IntegerFilter numeroPrelieviPositiviSulTotale;

    private IntegerFilter totalePrelievi; */

    private BooleanFilter pregressaChirurgiaProstatica;

  /*  private BooleanFilter terapiaInibitori5AlfaReduttasi;

    private BooleanFilter terapiaAntiandrogenicaNeoadiuvante;

    private BooleanFilter radioterapiaPelvi;

    private TipoRischioFilter rischio;

    private LocalDateFilter dataIntervento;

    private StringFilter esameIstologicoT;

    private StringFilter esameIstologicoN;

    private StringFilter esameIstologicoGleasonScoreComposizione;

    private IntegerFilter esameIstologicoGleasonScore; */

    public CampioneBiologicoCriteria() {
    }

   /* public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }
	*/

    public StringFilter getCodRH() {
        return codRH;
    }

    public void setCodRH(StringFilter codRH) {
        this.codRH = codRH;
    }

    public LongFilter getNumeroCartellaClinica() {
        return numeroCartellaClinica;
    }

    public void setNumeroCartellaClinica(LongFilter numeroCartellaClinica) {
        this.numeroCartellaClinica = numeroCartellaClinica;
    }

   /* public StringFilter getCodUMG() {
        return codUMG;
    }

    public void setCodUMG(StringFilter codUMG) {
        this.codUMG = codUMG;
    }

    public LocalDateFilter getDataReclutament() {
        return dataReclutament;
    }

    public void setDataReclutament(LocalDateFilter dataReclutament) {
        this.dataReclutament = dataReclutament;
    }

    public IntegerFilter getEtaPaziente() {
        return etaPaziente;
    }

    public void setEtaPaziente(IntegerFilter etaPaziente) {
        this.etaPaziente = etaPaziente;
    }

    public IntegerFilter getDimensioneGhiandolaProstatica() {
        return dimensioneGhiandolaProstatica;
    }

    public void setDimensioneGhiandolaProstatica(IntegerFilter dimensioneGhiandolaProstatica) {
        this.dimensioneGhiandolaProstatica = dimensioneGhiandolaProstatica;
    }
*/
    public TipoCampioneFilter getTipoCampione() {
        return tipoCampione;
    }

    public void setTipoCampione(TipoCampioneFilter tipoCampione) {
        this.tipoCampione = tipoCampione;
    }
/*
    public StringFilter getValutazionePCA3() {
        return valutazionePCA3;
    }

    public void setValutazionePCA3(StringFilter valutazionePCA3) {
        this.valutazionePCA3 = valutazionePCA3;
    }

    public FloatFilter getPsaTotale() {
        return psaTotale;
    }

    public void setPsaTotale(FloatFilter psaTotale) {
        this.psaTotale = psaTotale;
    }

    public FloatFilter getRapportoFT() {
        return rapportoFT;
    }

    public void setRapportoFT(FloatFilter rapportoFT) {
        this.rapportoFT = rapportoFT;
    }

    public FloatFilter getPsaFree() {
        return psaFree;
    }

    public void setPsaFree(FloatFilter psaFree) {
        this.psaFree = psaFree;
    }
*/
    public TipoMalattiaFilter getMalattia() {
        return malattia;
    }

    public void setMalattia(TipoMalattiaFilter malattia) {
        this.malattia = malattia;
    }

  /*  public LocalDateFilter getDataBiopsia() {
        return dataBiopsia;
    }

    public void setDataBiopsia(LocalDateFilter dataBiopsia) {
        this.dataBiopsia = dataBiopsia;
    }

    public StringFilter getEsitoBiopsiaProstaticaGleasonScoreComposizione() {
        return esitoBiopsiaProstaticaGleasonScoreComposizione;
    }

    public void setEsitoBiopsiaProstaticaGleasonScoreComposizione(StringFilter esitoBiopsiaProstaticaGleasonScoreComposizione) {
        this.esitoBiopsiaProstaticaGleasonScoreComposizione = esitoBiopsiaProstaticaGleasonScoreComposizione;
    }

    public StringFilter getEsitoBiopsiaProstaticaGleasonScore() {
        return esitoBiopsiaProstaticaGleasonScore;
    }

    public void setEsitoBiopsiaProstaticaGleasonScore(StringFilter esitoBiopsiaProstaticaGleasonScore) {
        this.esitoBiopsiaProstaticaGleasonScore = esitoBiopsiaProstaticaGleasonScore;
    }

    public IntegerFilter getNumeroPrelieviPositiviSulTotale() {
        return numeroPrelieviPositiviSulTotale;
    }

    public void setNumeroPrelieviPositiviSulTotale(IntegerFilter numeroPrelieviPositiviSulTotale) {
        this.numeroPrelieviPositiviSulTotale = numeroPrelieviPositiviSulTotale;
    }

    public IntegerFilter getTotalePrelievi() {
        return totalePrelievi;
    }

    public void setTotalePrelievi(IntegerFilter totalePrelievi) {
        this.totalePrelievi = totalePrelievi;
    }
*/
    public BooleanFilter getPregressaChirurgiaProstatica() {
        return pregressaChirurgiaProstatica;
    }

    public void setPregressaChirurgiaProstatica(BooleanFilter pregressaChirurgiaProstatica) {
        this.pregressaChirurgiaProstatica = pregressaChirurgiaProstatica;
    }
/*
    public BooleanFilter getTerapiaInibitori5AlfaReduttasi() {
        return terapiaInibitori5AlfaReduttasi;
    }

    public void setTerapiaInibitori5AlfaReduttasi(BooleanFilter terapiaInibitori5AlfaReduttasi) {
        this.terapiaInibitori5AlfaReduttasi = terapiaInibitori5AlfaReduttasi;
    }

    public BooleanFilter getTerapiaAntiandrogenicaNeoadiuvante() {
        return terapiaAntiandrogenicaNeoadiuvante;
    }

    public void setTerapiaAntiandrogenicaNeoadiuvante(BooleanFilter terapiaAntiandrogenicaNeoadiuvante) {
        this.terapiaAntiandrogenicaNeoadiuvante = terapiaAntiandrogenicaNeoadiuvante;
    }

    public BooleanFilter getRadioterapiaPelvi() {
        return radioterapiaPelvi;
    }

    public void setRadioterapiaPelvi(BooleanFilter radioterapiaPelvi) {
        this.radioterapiaPelvi = radioterapiaPelvi;
    }

    public TipoRischioFilter getRischio() {
        return rischio;
    }

    public void setRischio(TipoRischioFilter rischio) {
        this.rischio = rischio;
    }

    public LocalDateFilter getDataIntervento() {
        return dataIntervento;
    }

    public void setDataIntervento(LocalDateFilter dataIntervento) {
        this.dataIntervento = dataIntervento;
    }

    public StringFilter getEsameIstologicoT() {
        return esameIstologicoT;
    }

    public void setEsameIstologicoT(StringFilter esameIstologicoT) {
        this.esameIstologicoT = esameIstologicoT;
    }

    public StringFilter getEsameIstologicoN() {
        return esameIstologicoN;
    }

    public void setEsameIstologicoN(StringFilter esameIstologicoN) {
        this.esameIstologicoN = esameIstologicoN;
    }

    public StringFilter getEsameIstologicoGleasonScoreComposizione() {
        return esameIstologicoGleasonScoreComposizione;
    }

    public void setEsameIstologicoGleasonScoreComposizione(StringFilter esameIstologicoGleasonScoreComposizione) {
        this.esameIstologicoGleasonScoreComposizione = esameIstologicoGleasonScoreComposizione;
    }

    public IntegerFilter getEsameIstologicoGleasonScore() {
        return esameIstologicoGleasonScore;
    }

    public void setEsameIstologicoGleasonScore(IntegerFilter esameIstologicoGleasonScore) {
        this.esameIstologicoGleasonScore = esameIstologicoGleasonScore;
    }*/

    @Override
    public String toString() {
        return "CampioneBiologicoCriteria{" +
            //    (id != null ? "id=" + id + ", " : "") +
                (codRH != null ? "codRH=" + codRH + ", " : "") +
                (numeroCartellaClinica != null ? "numeroCartellaClinica=" + numeroCartellaClinica + ", " : "") +
              //  (codUMG != null ? "codUMG=" + codUMG + ", " : "") +
               // (dataReclutament != null ? "dataReclutament=" + dataReclutament + ", " : "") +
             //   (etaPaziente != null ? "etaPaziente=" + etaPaziente + ", " : "") +
           //     (dimensioneGhiandolaProstatica != null ? "dimensioneGhiandolaProstatica=" + dimensioneGhiandolaProstatica + ", " : "") +
                (tipoCampione != null ? "tipoCampione=" + tipoCampione + ", " : "") +
             //   (valutazionePCA3 != null ? "valutazionePCA3=" + valutazionePCA3 + ", " : "") +
             //   (psaTotale != null ? "psaTotale=" + psaTotale + ", " : "") +
             //   (rapportoFT != null ? "rapportoFT=" + rapportoFT + ", " : "") +
             //   (psaFree != null ? "psaFree=" + psaFree + ", " : "") +
              (malattia != null ? "malattia=" + malattia + ", " : "") +
            //    (dataBiopsia != null ? "dataBiopsia=" + dataBiopsia + ", " : "") +
             //   (esitoBiopsiaProstaticaGleasonScoreComposizione != null ? "esitoBiopsiaProstaticaGleasonScoreComposizione=" + esitoBiopsiaProstaticaGleasonScoreComposizione + ", " : "") +
            //    (esitoBiopsiaProstaticaGleasonScore != null ? "esitoBiopsiaProstaticaGleasonScore=" + esitoBiopsiaProstaticaGleasonScore + ", " : "") +
             //   (numeroPrelieviPositiviSulTotale != null ? "numeroPrelieviPositiviSulTotale=" + numeroPrelieviPositiviSulTotale + ", " : "") +
             //   (totalePrelievi != null ? "totalePrelievi=" + totalePrelievi + ", " : "") +
                (pregressaChirurgiaProstatica != null ? "pregressaChirurgiaProstatica=" + pregressaChirurgiaProstatica + ", " : "") +
            //    (terapiaInibitori5AlfaReduttasi != null ? "terapiaInibitori5AlfaReduttasi=" + terapiaInibitori5AlfaReduttasi + ", " : "") +
            //    (terapiaAntiandrogenicaNeoadiuvante != null ? "terapiaAntiandrogenicaNeoadiuvante=" + terapiaAntiandrogenicaNeoadiuvante + ", " : "") +
            //    (radioterapiaPelvi != null ? "radioterapiaPelvi=" + radioterapiaPelvi + ", " : "") +
            //    (rischio != null ? "rischio=" + rischio + ", " : "") +
            //    (dataIntervento != null ? "dataIntervento=" + dataIntervento + ", " : "") +
            //    (esameIstologicoT != null ? "esameIstologicoT=" + esameIstologicoT + ", " : "") +
            //    (esameIstologicoN != null ? "esameIstologicoN=" + esameIstologicoN + ", " : "") +
            //    (esameIstologicoGleasonScoreComposizione != null ? "esameIstologicoGleasonScoreComposizione=" + esameIstologicoGleasonScoreComposizione + ", " : "") +
            //    (esameIstologicoGleasonScore != null ? "esameIstologicoGleasonScore=" + esameIstologicoGleasonScore + ", " : "") +
            "}";
    }

}
