package it.vtsolutions.web.rest;

import it.vtsolutions.InnoprostApp;

import it.vtsolutions.domain.CampioneBiologico;
import it.vtsolutions.repository.CampioneBiologicoRepository;
import it.vtsolutions.service.CampioneBiologicoService;
import it.vtsolutions.service.dto.CampioneBiologicoDTO;
import it.vtsolutions.service.mapper.CampioneBiologicoMapper;
import it.vtsolutions.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static it.vtsolutions.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import it.vtsolutions.domain.enumeration.TipoCampione;
import it.vtsolutions.domain.enumeration.TipoMalattia;
import it.vtsolutions.domain.enumeration.TipoRischio;
/**
 * Test class for the CampioneBiologicoResource REST controller.
 *
 * @see CampioneBiologicoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InnoprostApp.class)
public class CampioneBiologicoResourceIntTest {

    private static final String DEFAULT_COD_RH = "AAAAAAAAAA";
    private static final String UPDATED_COD_RH = "BBBBBBBBBB";

    private static final Long DEFAULT_NUMERO_CARTELLA_CLINICA = 1L;
    private static final Long UPDATED_NUMERO_CARTELLA_CLINICA = 2L;

    private static final String DEFAULT_COD_UMG = "AAAAAAAAAA";
    private static final String UPDATED_COD_UMG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA_ESECUZIONE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_ESECUZIONE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ETA_PAZIENTE = 1;
    private static final Integer UPDATED_ETA_PAZIENTE = 2;

    private static final Integer DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA = 1;
    private static final Integer UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA = 2;

    private static final TipoCampione DEFAULT_TIPO_CAMPIONE = TipoCampione.EMATICO;
    private static final TipoCampione UPDATED_TIPO_CAMPIONE = TipoCampione.URINE;

    private static final String DEFAULT_VALUTAZIONE_PCA_3 = "AAAAAAAAAA";
    private static final String UPDATED_VALUTAZIONE_PCA_3 = "BBBBBBBBBB";

    private static final Float DEFAULT_PSA_TOTALE = 1F;
    private static final Float UPDATED_PSA_TOTALE = 2F;

    private static final Float DEFAULT_RAPPORTO_FT = 1F;
    private static final Float UPDATED_RAPPORTO_FT = 2F;

    private static final Float DEFAULT_PSA_FREE = 1F;
    private static final Float UPDATED_PSA_FREE = 2F;

    private static final TipoMalattia DEFAULT_MALATTIA = TipoMalattia.PCA;
    private static final TipoMalattia UPDATED_MALATTIA = TipoMalattia.IPB;

    private static final LocalDate DEFAULT_DATA_BIOPSIA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_BIOPSIA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE = 1;
    private static final Integer UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE = 2;

    private static final Integer DEFAULT_TOTALE_PRELIEVI = 1;
    private static final Integer UPDATED_TOTALE_PRELIEVI = 2;

    private static final Boolean DEFAULT_PREGRESSA_CHIRURGIA_PROSTATICA = false;
    private static final Boolean UPDATED_PREGRESSA_CHIRURGIA_PROSTATICA = true;

    private static final Boolean DEFAULT_TERAPIA_INIBITORI_5_ALFA_REDUTTASI = false;
    private static final Boolean UPDATED_TERAPIA_INIBITORI_5_ALFA_REDUTTASI = true;

    private static final Boolean DEFAULT_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE = false;
    private static final Boolean UPDATED_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE = true;

    private static final Boolean DEFAULT_RADIOTERAPIA_PELVI = false;
    private static final Boolean UPDATED_RADIOTERAPIA_PELVI = true;

    private static final TipoRischio DEFAULT_RISCHIO = TipoRischio.BASSO;
    private static final TipoRischio UPDATED_RISCHIO = TipoRischio.MEDIO;

    private static final LocalDate DEFAULT_DATA_INTERVENTO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_INTERVENTO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ESAME_ISTOLOGICO_T = "AAAAAAAAAA";
    private static final String UPDATED_ESAME_ISTOLOGICO_T = "BBBBBBBBBB";

    private static final String DEFAULT_ESAME_ISTOLOGICO_N = "AAAAAAAAAA";
    private static final String UPDATED_ESAME_ISTOLOGICO_N = "BBBBBBBBBB";

    private static final String DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE = 1;
    private static final Integer UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE = 2;

    @Autowired
    private CampioneBiologicoRepository campioneBiologicoRepository;


    @Autowired
    private CampioneBiologicoMapper campioneBiologicoMapper;
    

    @Autowired
    private CampioneBiologicoService campioneBiologicoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCampioneBiologicoMockMvc;

    private CampioneBiologico campioneBiologico;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampioneBiologicoResource campioneBiologicoResource = new CampioneBiologicoResource(campioneBiologicoService);
        this.restCampioneBiologicoMockMvc = MockMvcBuilders.standaloneSetup(campioneBiologicoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampioneBiologico createEntity(EntityManager em) {
        CampioneBiologico campioneBiologico = new CampioneBiologico()
            .codRH(DEFAULT_COD_RH)
            .numeroCartellaClinica(DEFAULT_NUMERO_CARTELLA_CLINICA)
            .codUMG(DEFAULT_COD_UMG)
            .dataEsecuzione(DEFAULT_DATA_ESECUZIONE)
            .etaPaziente(DEFAULT_ETA_PAZIENTE)
            .dimensioneGhiandolaProstatica(DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA)
            .tipoCampione(DEFAULT_TIPO_CAMPIONE)
            .valutazionePCA3(DEFAULT_VALUTAZIONE_PCA_3)
            .psaTotale(DEFAULT_PSA_TOTALE)
            .rapportoFT(DEFAULT_RAPPORTO_FT)
            .psaFree(DEFAULT_PSA_FREE)
            .malattia(DEFAULT_MALATTIA)
            .dataBiopsia(DEFAULT_DATA_BIOPSIA)
            .esitoBiopsiaProstaticaGleasonScoreComposizione(DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE)
            .esitoBiopsiaProstaticaGleasonScore(DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE)
            .numeroPrelieviPositiviSulTotale(DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE)
            .totalePrelievi(DEFAULT_TOTALE_PRELIEVI)
            .pregressaChirurgiaProstatica(DEFAULT_PREGRESSA_CHIRURGIA_PROSTATICA)
            .terapiaInibitori5AlfaReduttasi(DEFAULT_TERAPIA_INIBITORI_5_ALFA_REDUTTASI)
            .terapiaAntiandrogenicaNeoadiuvante(DEFAULT_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE)
            .radioterapiaPelvi(DEFAULT_RADIOTERAPIA_PELVI)
            .rischio(DEFAULT_RISCHIO)
            .dataIntervento(DEFAULT_DATA_INTERVENTO)
            .esameIstologicoT(DEFAULT_ESAME_ISTOLOGICO_T)
            .esameIstologicoN(DEFAULT_ESAME_ISTOLOGICO_N)
            .esameIstologicoGleasonScoreComposizione(DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE)
            .esameIstologicoGleasonScore(DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE);
        return campioneBiologico;
    }

    @Before
    public void initTest() {
        campioneBiologico = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampioneBiologico() throws Exception {
        int databaseSizeBeforeCreate = campioneBiologicoRepository.findAll().size();

        // Create the CampioneBiologico
        CampioneBiologicoDTO campioneBiologicoDTO = campioneBiologicoMapper.toDto(campioneBiologico);
        restCampioneBiologicoMockMvc.perform(post("/api/campione-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campioneBiologicoDTO)))
            .andExpect(status().isCreated());

        // Validate the CampioneBiologico in the database
        List<CampioneBiologico> campioneBiologicoList = campioneBiologicoRepository.findAll();
        assertThat(campioneBiologicoList).hasSize(databaseSizeBeforeCreate + 1);
        CampioneBiologico testCampioneBiologico = campioneBiologicoList.get(campioneBiologicoList.size() - 1);
        assertThat(testCampioneBiologico.getCodRH()).isEqualTo(DEFAULT_COD_RH);
        assertThat(testCampioneBiologico.getNumeroCartellaClinica()).isEqualTo(DEFAULT_NUMERO_CARTELLA_CLINICA);
        assertThat(testCampioneBiologico.getCodUMG()).isEqualTo(DEFAULT_COD_UMG);
        assertThat(testCampioneBiologico.getDataEsecuzione()).isEqualTo(DEFAULT_DATA_ESECUZIONE);
        assertThat(testCampioneBiologico.getEtaPaziente()).isEqualTo(DEFAULT_ETA_PAZIENTE);
        assertThat(testCampioneBiologico.getDimensioneGhiandolaProstatica()).isEqualTo(DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA);
        assertThat(testCampioneBiologico.getTipoCampione()).isEqualTo(DEFAULT_TIPO_CAMPIONE);
        assertThat(testCampioneBiologico.getValutazionePCA3()).isEqualTo(DEFAULT_VALUTAZIONE_PCA_3);
        assertThat(testCampioneBiologico.getPsaTotale()).isEqualTo(DEFAULT_PSA_TOTALE);
        assertThat(testCampioneBiologico.getRapportoFT()).isEqualTo(DEFAULT_RAPPORTO_FT);
        assertThat(testCampioneBiologico.getPsaFree()).isEqualTo(DEFAULT_PSA_FREE);
        assertThat(testCampioneBiologico.getMalattia()).isEqualTo(DEFAULT_MALATTIA);
        assertThat(testCampioneBiologico.getDataBiopsia()).isEqualTo(DEFAULT_DATA_BIOPSIA);
        assertThat(testCampioneBiologico.getEsitoBiopsiaProstaticaGleasonScoreComposizione()).isEqualTo(DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE);
        assertThat(testCampioneBiologico.getEsitoBiopsiaProstaticaGleasonScore()).isEqualTo(DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE);
        assertThat(testCampioneBiologico.getNumeroPrelieviPositiviSulTotale()).isEqualTo(DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);
        assertThat(testCampioneBiologico.getTotalePrelievi()).isEqualTo(DEFAULT_TOTALE_PRELIEVI);
        assertThat(testCampioneBiologico.isPregressaChirurgiaProstatica()).isEqualTo(DEFAULT_PREGRESSA_CHIRURGIA_PROSTATICA);
        assertThat(testCampioneBiologico.isTerapiaInibitori5AlfaReduttasi()).isEqualTo(DEFAULT_TERAPIA_INIBITORI_5_ALFA_REDUTTASI);
        assertThat(testCampioneBiologico.isTerapiaAntiandrogenicaNeoadiuvante()).isEqualTo(DEFAULT_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE);
        assertThat(testCampioneBiologico.isRadioterapiaPelvi()).isEqualTo(DEFAULT_RADIOTERAPIA_PELVI);
        assertThat(testCampioneBiologico.getRischio()).isEqualTo(DEFAULT_RISCHIO);
        assertThat(testCampioneBiologico.getDataIntervento()).isEqualTo(DEFAULT_DATA_INTERVENTO);
        assertThat(testCampioneBiologico.getEsameIstologicoT()).isEqualTo(DEFAULT_ESAME_ISTOLOGICO_T);
        assertThat(testCampioneBiologico.getEsameIstologicoN()).isEqualTo(DEFAULT_ESAME_ISTOLOGICO_N);
        assertThat(testCampioneBiologico.getEsameIstologicoGleasonScoreComposizione()).isEqualTo(DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE);
        assertThat(testCampioneBiologico.getEsameIstologicoGleasonScore()).isEqualTo(DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE);
    }

    @Test
    @Transactional
    public void createCampioneBiologicoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campioneBiologicoRepository.findAll().size();

        // Create the CampioneBiologico with an existing ID
        campioneBiologico.setId(1L);
        CampioneBiologicoDTO campioneBiologicoDTO = campioneBiologicoMapper.toDto(campioneBiologico);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampioneBiologicoMockMvc.perform(post("/api/campione-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campioneBiologicoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampioneBiologico in the database
        List<CampioneBiologico> campioneBiologicoList = campioneBiologicoRepository.findAll();
        assertThat(campioneBiologicoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicos() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList
        restCampioneBiologicoMockMvc.perform(get("/api/campione-biologicos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campioneBiologico.getId().intValue())))
            .andExpect(jsonPath("$.[*].codRH").value(hasItem(DEFAULT_COD_RH.toString())))
            .andExpect(jsonPath("$.[*].numeroCartellaClinica").value(hasItem(DEFAULT_NUMERO_CARTELLA_CLINICA.intValue())))
            .andExpect(jsonPath("$.[*].codUMG").value(hasItem(DEFAULT_COD_UMG.toString())))
            .andExpect(jsonPath("$.[*].dataEsecuzione").value(hasItem(DEFAULT_DATA_ESECUZIONE.toString())))
            .andExpect(jsonPath("$.[*].etaPaziente").value(hasItem(DEFAULT_ETA_PAZIENTE)))
            .andExpect(jsonPath("$.[*].dimensioneGhiandolaProstatica").value(hasItem(DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA)))
            .andExpect(jsonPath("$.[*].tipoCampione").value(hasItem(DEFAULT_TIPO_CAMPIONE.toString())))
            .andExpect(jsonPath("$.[*].valutazionePCA3").value(hasItem(DEFAULT_VALUTAZIONE_PCA_3.toString())))
            .andExpect(jsonPath("$.[*].psaTotale").value(hasItem(DEFAULT_PSA_TOTALE.doubleValue())))
            .andExpect(jsonPath("$.[*].rapportoFT").value(hasItem(DEFAULT_RAPPORTO_FT.doubleValue())))
            .andExpect(jsonPath("$.[*].psaFree").value(hasItem(DEFAULT_PSA_FREE.doubleValue())))
            .andExpect(jsonPath("$.[*].malattia").value(hasItem(DEFAULT_MALATTIA.toString())))
            .andExpect(jsonPath("$.[*].dataBiopsia").value(hasItem(DEFAULT_DATA_BIOPSIA.toString())))
            .andExpect(jsonPath("$.[*].esitoBiopsiaProstaticaGleasonScoreComposizione").value(hasItem(DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE.toString())))
            .andExpect(jsonPath("$.[*].esitoBiopsiaProstaticaGleasonScore").value(hasItem(DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE.toString())))
            .andExpect(jsonPath("$.[*].numeroPrelieviPositiviSulTotale").value(hasItem(DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE)))
            .andExpect(jsonPath("$.[*].totalePrelievi").value(hasItem(DEFAULT_TOTALE_PRELIEVI)))
            .andExpect(jsonPath("$.[*].pregressaChirurgiaProstatica").value(hasItem(DEFAULT_PREGRESSA_CHIRURGIA_PROSTATICA.booleanValue())))
            .andExpect(jsonPath("$.[*].terapiaInibitori5AlfaReduttasi").value(hasItem(DEFAULT_TERAPIA_INIBITORI_5_ALFA_REDUTTASI.booleanValue())))
            .andExpect(jsonPath("$.[*].terapiaAntiandrogenicaNeoadiuvante").value(hasItem(DEFAULT_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE.booleanValue())))
            .andExpect(jsonPath("$.[*].radioterapiaPelvi").value(hasItem(DEFAULT_RADIOTERAPIA_PELVI.booleanValue())))
            .andExpect(jsonPath("$.[*].rischio").value(hasItem(DEFAULT_RISCHIO.toString())))
            .andExpect(jsonPath("$.[*].dataIntervento").value(hasItem(DEFAULT_DATA_INTERVENTO.toString())))
            .andExpect(jsonPath("$.[*].esameIstologicoT").value(hasItem(DEFAULT_ESAME_ISTOLOGICO_T.toString())))
            .andExpect(jsonPath("$.[*].esameIstologicoN").value(hasItem(DEFAULT_ESAME_ISTOLOGICO_N.toString())))
            .andExpect(jsonPath("$.[*].esameIstologicoGleasonScoreComposizione").value(hasItem(DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE.toString())))
            .andExpect(jsonPath("$.[*].esameIstologicoGleasonScore").value(hasItem(DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE)));
    }
    

    @Test
    @Transactional
    public void getCampioneBiologico() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get the campioneBiologico
        restCampioneBiologicoMockMvc.perform(get("/api/campione-biologicos/{id}", campioneBiologico.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campioneBiologico.getId().intValue()))
            .andExpect(jsonPath("$.codRH").value(DEFAULT_COD_RH.toString()))
            .andExpect(jsonPath("$.numeroCartellaClinica").value(DEFAULT_NUMERO_CARTELLA_CLINICA.intValue()))
            .andExpect(jsonPath("$.codUMG").value(DEFAULT_COD_UMG.toString()))
            .andExpect(jsonPath("$.dataEsecuzione").value(DEFAULT_DATA_ESECUZIONE.toString()))
            .andExpect(jsonPath("$.etaPaziente").value(DEFAULT_ETA_PAZIENTE))
            .andExpect(jsonPath("$.dimensioneGhiandolaProstatica").value(DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA))
            .andExpect(jsonPath("$.tipoCampione").value(DEFAULT_TIPO_CAMPIONE.toString()))
            .andExpect(jsonPath("$.valutazionePCA3").value(DEFAULT_VALUTAZIONE_PCA_3.toString()))
            .andExpect(jsonPath("$.psaTotale").value(DEFAULT_PSA_TOTALE.doubleValue()))
            .andExpect(jsonPath("$.rapportoFT").value(DEFAULT_RAPPORTO_FT.doubleValue()))
            .andExpect(jsonPath("$.psaFree").value(DEFAULT_PSA_FREE.doubleValue()))
            .andExpect(jsonPath("$.malattia").value(DEFAULT_MALATTIA.toString()))
            .andExpect(jsonPath("$.dataBiopsia").value(DEFAULT_DATA_BIOPSIA.toString()))
            .andExpect(jsonPath("$.esitoBiopsiaProstaticaGleasonScoreComposizione").value(DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE.toString()))
            .andExpect(jsonPath("$.esitoBiopsiaProstaticaGleasonScore").value(DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE.toString()))
            .andExpect(jsonPath("$.numeroPrelieviPositiviSulTotale").value(DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE))
            .andExpect(jsonPath("$.totalePrelievi").value(DEFAULT_TOTALE_PRELIEVI))
            .andExpect(jsonPath("$.pregressaChirurgiaProstatica").value(DEFAULT_PREGRESSA_CHIRURGIA_PROSTATICA.booleanValue()))
            .andExpect(jsonPath("$.terapiaInibitori5AlfaReduttasi").value(DEFAULT_TERAPIA_INIBITORI_5_ALFA_REDUTTASI.booleanValue()))
            .andExpect(jsonPath("$.terapiaAntiandrogenicaNeoadiuvante").value(DEFAULT_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE.booleanValue()))
            .andExpect(jsonPath("$.radioterapiaPelvi").value(DEFAULT_RADIOTERAPIA_PELVI.booleanValue()))
            .andExpect(jsonPath("$.rischio").value(DEFAULT_RISCHIO.toString()))
            .andExpect(jsonPath("$.dataIntervento").value(DEFAULT_DATA_INTERVENTO.toString()))
            .andExpect(jsonPath("$.esameIstologicoT").value(DEFAULT_ESAME_ISTOLOGICO_T.toString()))
            .andExpect(jsonPath("$.esameIstologicoN").value(DEFAULT_ESAME_ISTOLOGICO_N.toString()))
            .andExpect(jsonPath("$.esameIstologicoGleasonScoreComposizione").value(DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE.toString()))
            .andExpect(jsonPath("$.esameIstologicoGleasonScore").value(DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE));
    }
    @Test
    @Transactional
    public void getNonExistingCampioneBiologico() throws Exception {
        // Get the campioneBiologico
        restCampioneBiologicoMockMvc.perform(get("/api/campione-biologicos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampioneBiologico() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        int databaseSizeBeforeUpdate = campioneBiologicoRepository.findAll().size();

        // Update the campioneBiologico
        CampioneBiologico updatedCampioneBiologico = campioneBiologicoRepository.findById(campioneBiologico.getId()).get();
        // Disconnect from session so that the updates on updatedCampioneBiologico are not directly saved in db
        em.detach(updatedCampioneBiologico);
        updatedCampioneBiologico
            .codRH(UPDATED_COD_RH)
            .numeroCartellaClinica(UPDATED_NUMERO_CARTELLA_CLINICA)
            .codUMG(UPDATED_COD_UMG)
            .dataEsecuzione(UPDATED_DATA_ESECUZIONE)
            .etaPaziente(UPDATED_ETA_PAZIENTE)
            .dimensioneGhiandolaProstatica(UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA)
            .tipoCampione(UPDATED_TIPO_CAMPIONE)
            .valutazionePCA3(UPDATED_VALUTAZIONE_PCA_3)
            .psaTotale(UPDATED_PSA_TOTALE)
            .rapportoFT(UPDATED_RAPPORTO_FT)
            .psaFree(UPDATED_PSA_FREE)
            .malattia(UPDATED_MALATTIA)
            .dataBiopsia(UPDATED_DATA_BIOPSIA)
            .esitoBiopsiaProstaticaGleasonScoreComposizione(UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE)
            .esitoBiopsiaProstaticaGleasonScore(UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE)
            .numeroPrelieviPositiviSulTotale(UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE)
            .totalePrelievi(UPDATED_TOTALE_PRELIEVI)
            .pregressaChirurgiaProstatica(UPDATED_PREGRESSA_CHIRURGIA_PROSTATICA)
            .terapiaInibitori5AlfaReduttasi(UPDATED_TERAPIA_INIBITORI_5_ALFA_REDUTTASI)
            .terapiaAntiandrogenicaNeoadiuvante(UPDATED_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE)
            .radioterapiaPelvi(UPDATED_RADIOTERAPIA_PELVI)
            .rischio(UPDATED_RISCHIO)
            .dataIntervento(UPDATED_DATA_INTERVENTO)
            .esameIstologicoT(UPDATED_ESAME_ISTOLOGICO_T)
            .esameIstologicoN(UPDATED_ESAME_ISTOLOGICO_N)
            .esameIstologicoGleasonScoreComposizione(UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE)
            .esameIstologicoGleasonScore(UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE);
        CampioneBiologicoDTO campioneBiologicoDTO = campioneBiologicoMapper.toDto(updatedCampioneBiologico);

        restCampioneBiologicoMockMvc.perform(put("/api/campione-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campioneBiologicoDTO)))
            .andExpect(status().isOk());

        // Validate the CampioneBiologico in the database
        List<CampioneBiologico> campioneBiologicoList = campioneBiologicoRepository.findAll();
        assertThat(campioneBiologicoList).hasSize(databaseSizeBeforeUpdate);
        CampioneBiologico testCampioneBiologico = campioneBiologicoList.get(campioneBiologicoList.size() - 1);
        assertThat(testCampioneBiologico.getCodRH()).isEqualTo(UPDATED_COD_RH);
        assertThat(testCampioneBiologico.getNumeroCartellaClinica()).isEqualTo(UPDATED_NUMERO_CARTELLA_CLINICA);
        assertThat(testCampioneBiologico.getCodUMG()).isEqualTo(UPDATED_COD_UMG);
        assertThat(testCampioneBiologico.getDataEsecuzione()).isEqualTo(UPDATED_DATA_ESECUZIONE);
        assertThat(testCampioneBiologico.getEtaPaziente()).isEqualTo(UPDATED_ETA_PAZIENTE);
        assertThat(testCampioneBiologico.getDimensioneGhiandolaProstatica()).isEqualTo(UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA);
        assertThat(testCampioneBiologico.getTipoCampione()).isEqualTo(UPDATED_TIPO_CAMPIONE);
        assertThat(testCampioneBiologico.getValutazionePCA3()).isEqualTo(UPDATED_VALUTAZIONE_PCA_3);
        assertThat(testCampioneBiologico.getPsaTotale()).isEqualTo(UPDATED_PSA_TOTALE);
        assertThat(testCampioneBiologico.getRapportoFT()).isEqualTo(UPDATED_RAPPORTO_FT);
        assertThat(testCampioneBiologico.getPsaFree()).isEqualTo(UPDATED_PSA_FREE);
        assertThat(testCampioneBiologico.getMalattia()).isEqualTo(UPDATED_MALATTIA);
        assertThat(testCampioneBiologico.getDataBiopsia()).isEqualTo(UPDATED_DATA_BIOPSIA);
        assertThat(testCampioneBiologico.getEsitoBiopsiaProstaticaGleasonScoreComposizione()).isEqualTo(UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE);
        assertThat(testCampioneBiologico.getEsitoBiopsiaProstaticaGleasonScore()).isEqualTo(UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE);
        assertThat(testCampioneBiologico.getNumeroPrelieviPositiviSulTotale()).isEqualTo(UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);
        assertThat(testCampioneBiologico.getTotalePrelievi()).isEqualTo(UPDATED_TOTALE_PRELIEVI);
        assertThat(testCampioneBiologico.isPregressaChirurgiaProstatica()).isEqualTo(UPDATED_PREGRESSA_CHIRURGIA_PROSTATICA);
        assertThat(testCampioneBiologico.isTerapiaInibitori5AlfaReduttasi()).isEqualTo(UPDATED_TERAPIA_INIBITORI_5_ALFA_REDUTTASI);
        assertThat(testCampioneBiologico.isTerapiaAntiandrogenicaNeoadiuvante()).isEqualTo(UPDATED_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE);
        assertThat(testCampioneBiologico.isRadioterapiaPelvi()).isEqualTo(UPDATED_RADIOTERAPIA_PELVI);
        assertThat(testCampioneBiologico.getRischio()).isEqualTo(UPDATED_RISCHIO);
        assertThat(testCampioneBiologico.getDataIntervento()).isEqualTo(UPDATED_DATA_INTERVENTO);
        assertThat(testCampioneBiologico.getEsameIstologicoT()).isEqualTo(UPDATED_ESAME_ISTOLOGICO_T);
        assertThat(testCampioneBiologico.getEsameIstologicoN()).isEqualTo(UPDATED_ESAME_ISTOLOGICO_N);
        assertThat(testCampioneBiologico.getEsameIstologicoGleasonScoreComposizione()).isEqualTo(UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE);
        assertThat(testCampioneBiologico.getEsameIstologicoGleasonScore()).isEqualTo(UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE);
    }

    @Test
    @Transactional
    public void updateNonExistingCampioneBiologico() throws Exception {
        int databaseSizeBeforeUpdate = campioneBiologicoRepository.findAll().size();

        // Create the CampioneBiologico
        CampioneBiologicoDTO campioneBiologicoDTO = campioneBiologicoMapper.toDto(campioneBiologico);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCampioneBiologicoMockMvc.perform(put("/api/campione-biologicos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campioneBiologicoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampioneBiologico in the database
        List<CampioneBiologico> campioneBiologicoList = campioneBiologicoRepository.findAll();
        assertThat(campioneBiologicoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampioneBiologico() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        int databaseSizeBeforeDelete = campioneBiologicoRepository.findAll().size();

        // Get the campioneBiologico
        restCampioneBiologicoMockMvc.perform(delete("/api/campione-biologicos/{id}", campioneBiologico.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CampioneBiologico> campioneBiologicoList = campioneBiologicoRepository.findAll();
        assertThat(campioneBiologicoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampioneBiologico.class);
        CampioneBiologico campioneBiologico1 = new CampioneBiologico();
        campioneBiologico1.setId(1L);
        CampioneBiologico campioneBiologico2 = new CampioneBiologico();
        campioneBiologico2.setId(campioneBiologico1.getId());
        assertThat(campioneBiologico1).isEqualTo(campioneBiologico2);
        campioneBiologico2.setId(2L);
        assertThat(campioneBiologico1).isNotEqualTo(campioneBiologico2);
        campioneBiologico1.setId(null);
        assertThat(campioneBiologico1).isNotEqualTo(campioneBiologico2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampioneBiologicoDTO.class);
        CampioneBiologicoDTO campioneBiologicoDTO1 = new CampioneBiologicoDTO();
        campioneBiologicoDTO1.setId(1L);
        CampioneBiologicoDTO campioneBiologicoDTO2 = new CampioneBiologicoDTO();
        assertThat(campioneBiologicoDTO1).isNotEqualTo(campioneBiologicoDTO2);
        campioneBiologicoDTO2.setId(campioneBiologicoDTO1.getId());
        assertThat(campioneBiologicoDTO1).isEqualTo(campioneBiologicoDTO2);
        campioneBiologicoDTO2.setId(2L);
        assertThat(campioneBiologicoDTO1).isNotEqualTo(campioneBiologicoDTO2);
        campioneBiologicoDTO1.setId(null);
        assertThat(campioneBiologicoDTO1).isNotEqualTo(campioneBiologicoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(campioneBiologicoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(campioneBiologicoMapper.fromId(null)).isNull();
    }
}
