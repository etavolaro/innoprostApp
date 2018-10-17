package it.vtsolutions.web.rest;

import it.vtsolutions.InnoprostApp;

import it.vtsolutions.domain.CampioneBiologico;
import it.vtsolutions.repository.CampioneBiologicoRepository;
import it.vtsolutions.service.CampioneBiologicoService;
import it.vtsolutions.service.dto.CampioneBiologicoDTO;
import it.vtsolutions.service.mapper.CampioneBiologicoMapper;
import it.vtsolutions.web.rest.errors.ExceptionTranslator;
import it.vtsolutions.service.dto.CampioneBiologicoCriteria;
import it.vtsolutions.service.CampioneBiologicoQueryService;

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

    private static final LocalDate DEFAULT_DATA_RECLUTAMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA_RECLUTAMENT = LocalDate.now(ZoneId.systemDefault());

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
    private CampioneBiologicoQueryService campioneBiologicoQueryService;

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
        final CampioneBiologicoResource campioneBiologicoResource = new CampioneBiologicoResource(campioneBiologicoService, campioneBiologicoQueryService);
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
            .dataReclutament(DEFAULT_DATA_RECLUTAMENT)
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
        assertThat(testCampioneBiologico.getDataReclutament()).isEqualTo(DEFAULT_DATA_RECLUTAMENT);
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
            .andExpect(jsonPath("$.[*].dataReclutament").value(hasItem(DEFAULT_DATA_RECLUTAMENT.toString())))
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
            .andExpect(jsonPath("$.dataReclutament").value(DEFAULT_DATA_RECLUTAMENT.toString()))
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
    public void getAllCampioneBiologicosByCodRHIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where codRH equals to DEFAULT_COD_RH
        defaultCampioneBiologicoShouldBeFound("codRH.equals=" + DEFAULT_COD_RH);

        // Get all the campioneBiologicoList where codRH equals to UPDATED_COD_RH
        defaultCampioneBiologicoShouldNotBeFound("codRH.equals=" + UPDATED_COD_RH);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByCodRHIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where codRH in DEFAULT_COD_RH or UPDATED_COD_RH
        defaultCampioneBiologicoShouldBeFound("codRH.in=" + DEFAULT_COD_RH + "," + UPDATED_COD_RH);

        // Get all the campioneBiologicoList where codRH equals to UPDATED_COD_RH
        defaultCampioneBiologicoShouldNotBeFound("codRH.in=" + UPDATED_COD_RH);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByCodRHIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where codRH is not null
        defaultCampioneBiologicoShouldBeFound("codRH.specified=true");

        // Get all the campioneBiologicoList where codRH is null
        defaultCampioneBiologicoShouldNotBeFound("codRH.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroCartellaClinicaIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroCartellaClinica equals to DEFAULT_NUMERO_CARTELLA_CLINICA
        defaultCampioneBiologicoShouldBeFound("numeroCartellaClinica.equals=" + DEFAULT_NUMERO_CARTELLA_CLINICA);

        // Get all the campioneBiologicoList where numeroCartellaClinica equals to UPDATED_NUMERO_CARTELLA_CLINICA
        defaultCampioneBiologicoShouldNotBeFound("numeroCartellaClinica.equals=" + UPDATED_NUMERO_CARTELLA_CLINICA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroCartellaClinicaIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroCartellaClinica in DEFAULT_NUMERO_CARTELLA_CLINICA or UPDATED_NUMERO_CARTELLA_CLINICA
        defaultCampioneBiologicoShouldBeFound("numeroCartellaClinica.in=" + DEFAULT_NUMERO_CARTELLA_CLINICA + "," + UPDATED_NUMERO_CARTELLA_CLINICA);

        // Get all the campioneBiologicoList where numeroCartellaClinica equals to UPDATED_NUMERO_CARTELLA_CLINICA
        defaultCampioneBiologicoShouldNotBeFound("numeroCartellaClinica.in=" + UPDATED_NUMERO_CARTELLA_CLINICA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroCartellaClinicaIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroCartellaClinica is not null
        defaultCampioneBiologicoShouldBeFound("numeroCartellaClinica.specified=true");

        // Get all the campioneBiologicoList where numeroCartellaClinica is null
        defaultCampioneBiologicoShouldNotBeFound("numeroCartellaClinica.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroCartellaClinicaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroCartellaClinica greater than or equals to DEFAULT_NUMERO_CARTELLA_CLINICA
        defaultCampioneBiologicoShouldBeFound("numeroCartellaClinica.greaterOrEqualThan=" + DEFAULT_NUMERO_CARTELLA_CLINICA);

        // Get all the campioneBiologicoList where numeroCartellaClinica greater than or equals to UPDATED_NUMERO_CARTELLA_CLINICA
        defaultCampioneBiologicoShouldNotBeFound("numeroCartellaClinica.greaterOrEqualThan=" + UPDATED_NUMERO_CARTELLA_CLINICA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroCartellaClinicaIsLessThanSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroCartellaClinica less than or equals to DEFAULT_NUMERO_CARTELLA_CLINICA
        defaultCampioneBiologicoShouldNotBeFound("numeroCartellaClinica.lessThan=" + DEFAULT_NUMERO_CARTELLA_CLINICA);

        // Get all the campioneBiologicoList where numeroCartellaClinica less than or equals to UPDATED_NUMERO_CARTELLA_CLINICA
        defaultCampioneBiologicoShouldBeFound("numeroCartellaClinica.lessThan=" + UPDATED_NUMERO_CARTELLA_CLINICA);
    }


    @Test
    @Transactional
    public void getAllCampioneBiologicosByCodUMGIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where codUMG equals to DEFAULT_COD_UMG
        defaultCampioneBiologicoShouldBeFound("codUMG.equals=" + DEFAULT_COD_UMG);

        // Get all the campioneBiologicoList where codUMG equals to UPDATED_COD_UMG
        defaultCampioneBiologicoShouldNotBeFound("codUMG.equals=" + UPDATED_COD_UMG);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByCodUMGIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where codUMG in DEFAULT_COD_UMG or UPDATED_COD_UMG
        defaultCampioneBiologicoShouldBeFound("codUMG.in=" + DEFAULT_COD_UMG + "," + UPDATED_COD_UMG);

        // Get all the campioneBiologicoList where codUMG equals to UPDATED_COD_UMG
        defaultCampioneBiologicoShouldNotBeFound("codUMG.in=" + UPDATED_COD_UMG);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByCodUMGIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where codUMG is not null
        defaultCampioneBiologicoShouldBeFound("codUMG.specified=true");

        // Get all the campioneBiologicoList where codUMG is null
        defaultCampioneBiologicoShouldNotBeFound("codUMG.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataReclutamentIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataReclutament equals to DEFAULT_DATA_RECLUTAMENT
        defaultCampioneBiologicoShouldBeFound("dataReclutament.equals=" + DEFAULT_DATA_RECLUTAMENT);

        // Get all the campioneBiologicoList where dataReclutament equals to UPDATED_DATA_RECLUTAMENT
        defaultCampioneBiologicoShouldNotBeFound("dataReclutament.equals=" + UPDATED_DATA_RECLUTAMENT);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataReclutamentIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataReclutament in DEFAULT_DATA_RECLUTAMENT or UPDATED_DATA_RECLUTAMENT
        defaultCampioneBiologicoShouldBeFound("dataReclutament.in=" + DEFAULT_DATA_RECLUTAMENT + "," + UPDATED_DATA_RECLUTAMENT);

        // Get all the campioneBiologicoList where dataReclutament equals to UPDATED_DATA_RECLUTAMENT
        defaultCampioneBiologicoShouldNotBeFound("dataReclutament.in=" + UPDATED_DATA_RECLUTAMENT);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataReclutamentIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataReclutament is not null
        defaultCampioneBiologicoShouldBeFound("dataReclutament.specified=true");

        // Get all the campioneBiologicoList where dataReclutament is null
        defaultCampioneBiologicoShouldNotBeFound("dataReclutament.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataReclutamentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataReclutament greater than or equals to DEFAULT_DATA_RECLUTAMENT
        defaultCampioneBiologicoShouldBeFound("dataReclutament.greaterOrEqualThan=" + DEFAULT_DATA_RECLUTAMENT);

        // Get all the campioneBiologicoList where dataReclutament greater than or equals to UPDATED_DATA_RECLUTAMENT
        defaultCampioneBiologicoShouldNotBeFound("dataReclutament.greaterOrEqualThan=" + UPDATED_DATA_RECLUTAMENT);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataReclutamentIsLessThanSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataReclutament less than or equals to DEFAULT_DATA_RECLUTAMENT
        defaultCampioneBiologicoShouldNotBeFound("dataReclutament.lessThan=" + DEFAULT_DATA_RECLUTAMENT);

        // Get all the campioneBiologicoList where dataReclutament less than or equals to UPDATED_DATA_RECLUTAMENT
        defaultCampioneBiologicoShouldBeFound("dataReclutament.lessThan=" + UPDATED_DATA_RECLUTAMENT);
    }


    @Test
    @Transactional
    public void getAllCampioneBiologicosByEtaPazienteIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where etaPaziente equals to DEFAULT_ETA_PAZIENTE
        defaultCampioneBiologicoShouldBeFound("etaPaziente.equals=" + DEFAULT_ETA_PAZIENTE);

        // Get all the campioneBiologicoList where etaPaziente equals to UPDATED_ETA_PAZIENTE
        defaultCampioneBiologicoShouldNotBeFound("etaPaziente.equals=" + UPDATED_ETA_PAZIENTE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEtaPazienteIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where etaPaziente in DEFAULT_ETA_PAZIENTE or UPDATED_ETA_PAZIENTE
        defaultCampioneBiologicoShouldBeFound("etaPaziente.in=" + DEFAULT_ETA_PAZIENTE + "," + UPDATED_ETA_PAZIENTE);

        // Get all the campioneBiologicoList where etaPaziente equals to UPDATED_ETA_PAZIENTE
        defaultCampioneBiologicoShouldNotBeFound("etaPaziente.in=" + UPDATED_ETA_PAZIENTE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEtaPazienteIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where etaPaziente is not null
        defaultCampioneBiologicoShouldBeFound("etaPaziente.specified=true");

        // Get all the campioneBiologicoList where etaPaziente is null
        defaultCampioneBiologicoShouldNotBeFound("etaPaziente.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEtaPazienteIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where etaPaziente greater than or equals to DEFAULT_ETA_PAZIENTE
        defaultCampioneBiologicoShouldBeFound("etaPaziente.greaterOrEqualThan=" + DEFAULT_ETA_PAZIENTE);

        // Get all the campioneBiologicoList where etaPaziente greater than or equals to UPDATED_ETA_PAZIENTE
        defaultCampioneBiologicoShouldNotBeFound("etaPaziente.greaterOrEqualThan=" + UPDATED_ETA_PAZIENTE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEtaPazienteIsLessThanSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where etaPaziente less than or equals to DEFAULT_ETA_PAZIENTE
        defaultCampioneBiologicoShouldNotBeFound("etaPaziente.lessThan=" + DEFAULT_ETA_PAZIENTE);

        // Get all the campioneBiologicoList where etaPaziente less than or equals to UPDATED_ETA_PAZIENTE
        defaultCampioneBiologicoShouldBeFound("etaPaziente.lessThan=" + UPDATED_ETA_PAZIENTE);
    }


    @Test
    @Transactional
    public void getAllCampioneBiologicosByDimensioneGhiandolaProstaticaIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica equals to DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA
        defaultCampioneBiologicoShouldBeFound("dimensioneGhiandolaProstatica.equals=" + DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA);

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica equals to UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA
        defaultCampioneBiologicoShouldNotBeFound("dimensioneGhiandolaProstatica.equals=" + UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDimensioneGhiandolaProstaticaIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica in DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA or UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA
        defaultCampioneBiologicoShouldBeFound("dimensioneGhiandolaProstatica.in=" + DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA + "," + UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA);

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica equals to UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA
        defaultCampioneBiologicoShouldNotBeFound("dimensioneGhiandolaProstatica.in=" + UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDimensioneGhiandolaProstaticaIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica is not null
        defaultCampioneBiologicoShouldBeFound("dimensioneGhiandolaProstatica.specified=true");

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica is null
        defaultCampioneBiologicoShouldNotBeFound("dimensioneGhiandolaProstatica.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDimensioneGhiandolaProstaticaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica greater than or equals to DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA
        defaultCampioneBiologicoShouldBeFound("dimensioneGhiandolaProstatica.greaterOrEqualThan=" + DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA);

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica greater than or equals to UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA
        defaultCampioneBiologicoShouldNotBeFound("dimensioneGhiandolaProstatica.greaterOrEqualThan=" + UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDimensioneGhiandolaProstaticaIsLessThanSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica less than or equals to DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA
        defaultCampioneBiologicoShouldNotBeFound("dimensioneGhiandolaProstatica.lessThan=" + DEFAULT_DIMENSIONE_GHIANDOLA_PROSTATICA);

        // Get all the campioneBiologicoList where dimensioneGhiandolaProstatica less than or equals to UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA
        defaultCampioneBiologicoShouldBeFound("dimensioneGhiandolaProstatica.lessThan=" + UPDATED_DIMENSIONE_GHIANDOLA_PROSTATICA);
    }


    @Test
    @Transactional
    public void getAllCampioneBiologicosByTipoCampioneIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where tipoCampione equals to DEFAULT_TIPO_CAMPIONE
        defaultCampioneBiologicoShouldBeFound("tipoCampione.equals=" + DEFAULT_TIPO_CAMPIONE);

        // Get all the campioneBiologicoList where tipoCampione equals to UPDATED_TIPO_CAMPIONE
        defaultCampioneBiologicoShouldNotBeFound("tipoCampione.equals=" + UPDATED_TIPO_CAMPIONE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTipoCampioneIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where tipoCampione in DEFAULT_TIPO_CAMPIONE or UPDATED_TIPO_CAMPIONE
        defaultCampioneBiologicoShouldBeFound("tipoCampione.in=" + DEFAULT_TIPO_CAMPIONE + "," + UPDATED_TIPO_CAMPIONE);

        // Get all the campioneBiologicoList where tipoCampione equals to UPDATED_TIPO_CAMPIONE
        defaultCampioneBiologicoShouldNotBeFound("tipoCampione.in=" + UPDATED_TIPO_CAMPIONE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTipoCampioneIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where tipoCampione is not null
        defaultCampioneBiologicoShouldBeFound("tipoCampione.specified=true");

        // Get all the campioneBiologicoList where tipoCampione is null
        defaultCampioneBiologicoShouldNotBeFound("tipoCampione.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByValutazionePCA3IsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where valutazionePCA3 equals to DEFAULT_VALUTAZIONE_PCA_3
        defaultCampioneBiologicoShouldBeFound("valutazionePCA3.equals=" + DEFAULT_VALUTAZIONE_PCA_3);

        // Get all the campioneBiologicoList where valutazionePCA3 equals to UPDATED_VALUTAZIONE_PCA_3
        defaultCampioneBiologicoShouldNotBeFound("valutazionePCA3.equals=" + UPDATED_VALUTAZIONE_PCA_3);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByValutazionePCA3IsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where valutazionePCA3 in DEFAULT_VALUTAZIONE_PCA_3 or UPDATED_VALUTAZIONE_PCA_3
        defaultCampioneBiologicoShouldBeFound("valutazionePCA3.in=" + DEFAULT_VALUTAZIONE_PCA_3 + "," + UPDATED_VALUTAZIONE_PCA_3);

        // Get all the campioneBiologicoList where valutazionePCA3 equals to UPDATED_VALUTAZIONE_PCA_3
        defaultCampioneBiologicoShouldNotBeFound("valutazionePCA3.in=" + UPDATED_VALUTAZIONE_PCA_3);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByValutazionePCA3IsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where valutazionePCA3 is not null
        defaultCampioneBiologicoShouldBeFound("valutazionePCA3.specified=true");

        // Get all the campioneBiologicoList where valutazionePCA3 is null
        defaultCampioneBiologicoShouldNotBeFound("valutazionePCA3.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByPsaTotaleIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where psaTotale equals to DEFAULT_PSA_TOTALE
        defaultCampioneBiologicoShouldBeFound("psaTotale.equals=" + DEFAULT_PSA_TOTALE);

        // Get all the campioneBiologicoList where psaTotale equals to UPDATED_PSA_TOTALE
        defaultCampioneBiologicoShouldNotBeFound("psaTotale.equals=" + UPDATED_PSA_TOTALE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByPsaTotaleIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where psaTotale in DEFAULT_PSA_TOTALE or UPDATED_PSA_TOTALE
        defaultCampioneBiologicoShouldBeFound("psaTotale.in=" + DEFAULT_PSA_TOTALE + "," + UPDATED_PSA_TOTALE);

        // Get all the campioneBiologicoList where psaTotale equals to UPDATED_PSA_TOTALE
        defaultCampioneBiologicoShouldNotBeFound("psaTotale.in=" + UPDATED_PSA_TOTALE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByPsaTotaleIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where psaTotale is not null
        defaultCampioneBiologicoShouldBeFound("psaTotale.specified=true");

        // Get all the campioneBiologicoList where psaTotale is null
        defaultCampioneBiologicoShouldNotBeFound("psaTotale.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByRapportoFTIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where rapportoFT equals to DEFAULT_RAPPORTO_FT
        defaultCampioneBiologicoShouldBeFound("rapportoFT.equals=" + DEFAULT_RAPPORTO_FT);

        // Get all the campioneBiologicoList where rapportoFT equals to UPDATED_RAPPORTO_FT
        defaultCampioneBiologicoShouldNotBeFound("rapportoFT.equals=" + UPDATED_RAPPORTO_FT);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByRapportoFTIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where rapportoFT in DEFAULT_RAPPORTO_FT or UPDATED_RAPPORTO_FT
        defaultCampioneBiologicoShouldBeFound("rapportoFT.in=" + DEFAULT_RAPPORTO_FT + "," + UPDATED_RAPPORTO_FT);

        // Get all the campioneBiologicoList where rapportoFT equals to UPDATED_RAPPORTO_FT
        defaultCampioneBiologicoShouldNotBeFound("rapportoFT.in=" + UPDATED_RAPPORTO_FT);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByRapportoFTIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where rapportoFT is not null
        defaultCampioneBiologicoShouldBeFound("rapportoFT.specified=true");

        // Get all the campioneBiologicoList where rapportoFT is null
        defaultCampioneBiologicoShouldNotBeFound("rapportoFT.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByPsaFreeIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where psaFree equals to DEFAULT_PSA_FREE
        defaultCampioneBiologicoShouldBeFound("psaFree.equals=" + DEFAULT_PSA_FREE);

        // Get all the campioneBiologicoList where psaFree equals to UPDATED_PSA_FREE
        defaultCampioneBiologicoShouldNotBeFound("psaFree.equals=" + UPDATED_PSA_FREE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByPsaFreeIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where psaFree in DEFAULT_PSA_FREE or UPDATED_PSA_FREE
        defaultCampioneBiologicoShouldBeFound("psaFree.in=" + DEFAULT_PSA_FREE + "," + UPDATED_PSA_FREE);

        // Get all the campioneBiologicoList where psaFree equals to UPDATED_PSA_FREE
        defaultCampioneBiologicoShouldNotBeFound("psaFree.in=" + UPDATED_PSA_FREE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByPsaFreeIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where psaFree is not null
        defaultCampioneBiologicoShouldBeFound("psaFree.specified=true");

        // Get all the campioneBiologicoList where psaFree is null
        defaultCampioneBiologicoShouldNotBeFound("psaFree.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByMalattiaIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where malattia equals to DEFAULT_MALATTIA
        defaultCampioneBiologicoShouldBeFound("malattia.equals=" + DEFAULT_MALATTIA);

        // Get all the campioneBiologicoList where malattia equals to UPDATED_MALATTIA
        defaultCampioneBiologicoShouldNotBeFound("malattia.equals=" + UPDATED_MALATTIA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByMalattiaIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where malattia in DEFAULT_MALATTIA or UPDATED_MALATTIA
        defaultCampioneBiologicoShouldBeFound("malattia.in=" + DEFAULT_MALATTIA + "," + UPDATED_MALATTIA);

        // Get all the campioneBiologicoList where malattia equals to UPDATED_MALATTIA
        defaultCampioneBiologicoShouldNotBeFound("malattia.in=" + UPDATED_MALATTIA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByMalattiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where malattia is not null
        defaultCampioneBiologicoShouldBeFound("malattia.specified=true");

        // Get all the campioneBiologicoList where malattia is null
        defaultCampioneBiologicoShouldNotBeFound("malattia.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataBiopsiaIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataBiopsia equals to DEFAULT_DATA_BIOPSIA
        defaultCampioneBiologicoShouldBeFound("dataBiopsia.equals=" + DEFAULT_DATA_BIOPSIA);

        // Get all the campioneBiologicoList where dataBiopsia equals to UPDATED_DATA_BIOPSIA
        defaultCampioneBiologicoShouldNotBeFound("dataBiopsia.equals=" + UPDATED_DATA_BIOPSIA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataBiopsiaIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataBiopsia in DEFAULT_DATA_BIOPSIA or UPDATED_DATA_BIOPSIA
        defaultCampioneBiologicoShouldBeFound("dataBiopsia.in=" + DEFAULT_DATA_BIOPSIA + "," + UPDATED_DATA_BIOPSIA);

        // Get all the campioneBiologicoList where dataBiopsia equals to UPDATED_DATA_BIOPSIA
        defaultCampioneBiologicoShouldNotBeFound("dataBiopsia.in=" + UPDATED_DATA_BIOPSIA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataBiopsiaIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataBiopsia is not null
        defaultCampioneBiologicoShouldBeFound("dataBiopsia.specified=true");

        // Get all the campioneBiologicoList where dataBiopsia is null
        defaultCampioneBiologicoShouldNotBeFound("dataBiopsia.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataBiopsiaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataBiopsia greater than or equals to DEFAULT_DATA_BIOPSIA
        defaultCampioneBiologicoShouldBeFound("dataBiopsia.greaterOrEqualThan=" + DEFAULT_DATA_BIOPSIA);

        // Get all the campioneBiologicoList where dataBiopsia greater than or equals to UPDATED_DATA_BIOPSIA
        defaultCampioneBiologicoShouldNotBeFound("dataBiopsia.greaterOrEqualThan=" + UPDATED_DATA_BIOPSIA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataBiopsiaIsLessThanSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataBiopsia less than or equals to DEFAULT_DATA_BIOPSIA
        defaultCampioneBiologicoShouldNotBeFound("dataBiopsia.lessThan=" + DEFAULT_DATA_BIOPSIA);

        // Get all the campioneBiologicoList where dataBiopsia less than or equals to UPDATED_DATA_BIOPSIA
        defaultCampioneBiologicoShouldBeFound("dataBiopsia.lessThan=" + UPDATED_DATA_BIOPSIA);
    }


    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsitoBiopsiaProstaticaGleasonScoreComposizioneIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScoreComposizione equals to DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE
        defaultCampioneBiologicoShouldBeFound("esitoBiopsiaProstaticaGleasonScoreComposizione.equals=" + DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScoreComposizione equals to UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE
        defaultCampioneBiologicoShouldNotBeFound("esitoBiopsiaProstaticaGleasonScoreComposizione.equals=" + UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsitoBiopsiaProstaticaGleasonScoreComposizioneIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScoreComposizione in DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE or UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE
        defaultCampioneBiologicoShouldBeFound("esitoBiopsiaProstaticaGleasonScoreComposizione.in=" + DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE + "," + UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScoreComposizione equals to UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE
        defaultCampioneBiologicoShouldNotBeFound("esitoBiopsiaProstaticaGleasonScoreComposizione.in=" + UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE_COMPOSIZIONE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsitoBiopsiaProstaticaGleasonScoreComposizioneIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScoreComposizione is not null
        defaultCampioneBiologicoShouldBeFound("esitoBiopsiaProstaticaGleasonScoreComposizione.specified=true");

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScoreComposizione is null
        defaultCampioneBiologicoShouldNotBeFound("esitoBiopsiaProstaticaGleasonScoreComposizione.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsitoBiopsiaProstaticaGleasonScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScore equals to DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE
        defaultCampioneBiologicoShouldBeFound("esitoBiopsiaProstaticaGleasonScore.equals=" + DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScore equals to UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE
        defaultCampioneBiologicoShouldNotBeFound("esitoBiopsiaProstaticaGleasonScore.equals=" + UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsitoBiopsiaProstaticaGleasonScoreIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScore in DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE or UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE
        defaultCampioneBiologicoShouldBeFound("esitoBiopsiaProstaticaGleasonScore.in=" + DEFAULT_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE + "," + UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScore equals to UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE
        defaultCampioneBiologicoShouldNotBeFound("esitoBiopsiaProstaticaGleasonScore.in=" + UPDATED_ESITO_BIOPSIA_PROSTATICA_GLEASON_SCORE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsitoBiopsiaProstaticaGleasonScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScore is not null
        defaultCampioneBiologicoShouldBeFound("esitoBiopsiaProstaticaGleasonScore.specified=true");

        // Get all the campioneBiologicoList where esitoBiopsiaProstaticaGleasonScore is null
        defaultCampioneBiologicoShouldNotBeFound("esitoBiopsiaProstaticaGleasonScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroPrelieviPositiviSulTotaleIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale equals to DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE
        defaultCampioneBiologicoShouldBeFound("numeroPrelieviPositiviSulTotale.equals=" + DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale equals to UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE
        defaultCampioneBiologicoShouldNotBeFound("numeroPrelieviPositiviSulTotale.equals=" + UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroPrelieviPositiviSulTotaleIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale in DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE or UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE
        defaultCampioneBiologicoShouldBeFound("numeroPrelieviPositiviSulTotale.in=" + DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE + "," + UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale equals to UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE
        defaultCampioneBiologicoShouldNotBeFound("numeroPrelieviPositiviSulTotale.in=" + UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroPrelieviPositiviSulTotaleIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale is not null
        defaultCampioneBiologicoShouldBeFound("numeroPrelieviPositiviSulTotale.specified=true");

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale is null
        defaultCampioneBiologicoShouldNotBeFound("numeroPrelieviPositiviSulTotale.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroPrelieviPositiviSulTotaleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale greater than or equals to DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE
        defaultCampioneBiologicoShouldBeFound("numeroPrelieviPositiviSulTotale.greaterOrEqualThan=" + DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale greater than or equals to UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE
        defaultCampioneBiologicoShouldNotBeFound("numeroPrelieviPositiviSulTotale.greaterOrEqualThan=" + UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByNumeroPrelieviPositiviSulTotaleIsLessThanSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale less than or equals to DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE
        defaultCampioneBiologicoShouldNotBeFound("numeroPrelieviPositiviSulTotale.lessThan=" + DEFAULT_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);

        // Get all the campioneBiologicoList where numeroPrelieviPositiviSulTotale less than or equals to UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE
        defaultCampioneBiologicoShouldBeFound("numeroPrelieviPositiviSulTotale.lessThan=" + UPDATED_NUMERO_PRELIEVI_POSITIVI_SUL_TOTALE);
    }


    @Test
    @Transactional
    public void getAllCampioneBiologicosByTotalePrelieviIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where totalePrelievi equals to DEFAULT_TOTALE_PRELIEVI
        defaultCampioneBiologicoShouldBeFound("totalePrelievi.equals=" + DEFAULT_TOTALE_PRELIEVI);

        // Get all the campioneBiologicoList where totalePrelievi equals to UPDATED_TOTALE_PRELIEVI
        defaultCampioneBiologicoShouldNotBeFound("totalePrelievi.equals=" + UPDATED_TOTALE_PRELIEVI);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTotalePrelieviIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where totalePrelievi in DEFAULT_TOTALE_PRELIEVI or UPDATED_TOTALE_PRELIEVI
        defaultCampioneBiologicoShouldBeFound("totalePrelievi.in=" + DEFAULT_TOTALE_PRELIEVI + "," + UPDATED_TOTALE_PRELIEVI);

        // Get all the campioneBiologicoList where totalePrelievi equals to UPDATED_TOTALE_PRELIEVI
        defaultCampioneBiologicoShouldNotBeFound("totalePrelievi.in=" + UPDATED_TOTALE_PRELIEVI);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTotalePrelieviIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where totalePrelievi is not null
        defaultCampioneBiologicoShouldBeFound("totalePrelievi.specified=true");

        // Get all the campioneBiologicoList where totalePrelievi is null
        defaultCampioneBiologicoShouldNotBeFound("totalePrelievi.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTotalePrelieviIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where totalePrelievi greater than or equals to DEFAULT_TOTALE_PRELIEVI
        defaultCampioneBiologicoShouldBeFound("totalePrelievi.greaterOrEqualThan=" + DEFAULT_TOTALE_PRELIEVI);

        // Get all the campioneBiologicoList where totalePrelievi greater than or equals to UPDATED_TOTALE_PRELIEVI
        defaultCampioneBiologicoShouldNotBeFound("totalePrelievi.greaterOrEqualThan=" + UPDATED_TOTALE_PRELIEVI);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTotalePrelieviIsLessThanSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where totalePrelievi less than or equals to DEFAULT_TOTALE_PRELIEVI
        defaultCampioneBiologicoShouldNotBeFound("totalePrelievi.lessThan=" + DEFAULT_TOTALE_PRELIEVI);

        // Get all the campioneBiologicoList where totalePrelievi less than or equals to UPDATED_TOTALE_PRELIEVI
        defaultCampioneBiologicoShouldBeFound("totalePrelievi.lessThan=" + UPDATED_TOTALE_PRELIEVI);
    }


    @Test
    @Transactional
    public void getAllCampioneBiologicosByPregressaChirurgiaProstaticaIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where pregressaChirurgiaProstatica equals to DEFAULT_PREGRESSA_CHIRURGIA_PROSTATICA
        defaultCampioneBiologicoShouldBeFound("pregressaChirurgiaProstatica.equals=" + DEFAULT_PREGRESSA_CHIRURGIA_PROSTATICA);

        // Get all the campioneBiologicoList where pregressaChirurgiaProstatica equals to UPDATED_PREGRESSA_CHIRURGIA_PROSTATICA
        defaultCampioneBiologicoShouldNotBeFound("pregressaChirurgiaProstatica.equals=" + UPDATED_PREGRESSA_CHIRURGIA_PROSTATICA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByPregressaChirurgiaProstaticaIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where pregressaChirurgiaProstatica in DEFAULT_PREGRESSA_CHIRURGIA_PROSTATICA or UPDATED_PREGRESSA_CHIRURGIA_PROSTATICA
        defaultCampioneBiologicoShouldBeFound("pregressaChirurgiaProstatica.in=" + DEFAULT_PREGRESSA_CHIRURGIA_PROSTATICA + "," + UPDATED_PREGRESSA_CHIRURGIA_PROSTATICA);

        // Get all the campioneBiologicoList where pregressaChirurgiaProstatica equals to UPDATED_PREGRESSA_CHIRURGIA_PROSTATICA
        defaultCampioneBiologicoShouldNotBeFound("pregressaChirurgiaProstatica.in=" + UPDATED_PREGRESSA_CHIRURGIA_PROSTATICA);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByPregressaChirurgiaProstaticaIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where pregressaChirurgiaProstatica is not null
        defaultCampioneBiologicoShouldBeFound("pregressaChirurgiaProstatica.specified=true");

        // Get all the campioneBiologicoList where pregressaChirurgiaProstatica is null
        defaultCampioneBiologicoShouldNotBeFound("pregressaChirurgiaProstatica.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTerapiaInibitori5AlfaReduttasiIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where terapiaInibitori5AlfaReduttasi equals to DEFAULT_TERAPIA_INIBITORI_5_ALFA_REDUTTASI
        defaultCampioneBiologicoShouldBeFound("terapiaInibitori5AlfaReduttasi.equals=" + DEFAULT_TERAPIA_INIBITORI_5_ALFA_REDUTTASI);

        // Get all the campioneBiologicoList where terapiaInibitori5AlfaReduttasi equals to UPDATED_TERAPIA_INIBITORI_5_ALFA_REDUTTASI
        defaultCampioneBiologicoShouldNotBeFound("terapiaInibitori5AlfaReduttasi.equals=" + UPDATED_TERAPIA_INIBITORI_5_ALFA_REDUTTASI);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTerapiaInibitori5AlfaReduttasiIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where terapiaInibitori5AlfaReduttasi in DEFAULT_TERAPIA_INIBITORI_5_ALFA_REDUTTASI or UPDATED_TERAPIA_INIBITORI_5_ALFA_REDUTTASI
        defaultCampioneBiologicoShouldBeFound("terapiaInibitori5AlfaReduttasi.in=" + DEFAULT_TERAPIA_INIBITORI_5_ALFA_REDUTTASI + "," + UPDATED_TERAPIA_INIBITORI_5_ALFA_REDUTTASI);

        // Get all the campioneBiologicoList where terapiaInibitori5AlfaReduttasi equals to UPDATED_TERAPIA_INIBITORI_5_ALFA_REDUTTASI
        defaultCampioneBiologicoShouldNotBeFound("terapiaInibitori5AlfaReduttasi.in=" + UPDATED_TERAPIA_INIBITORI_5_ALFA_REDUTTASI);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTerapiaInibitori5AlfaReduttasiIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where terapiaInibitori5AlfaReduttasi is not null
        defaultCampioneBiologicoShouldBeFound("terapiaInibitori5AlfaReduttasi.specified=true");

        // Get all the campioneBiologicoList where terapiaInibitori5AlfaReduttasi is null
        defaultCampioneBiologicoShouldNotBeFound("terapiaInibitori5AlfaReduttasi.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTerapiaAntiandrogenicaNeoadiuvanteIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where terapiaAntiandrogenicaNeoadiuvante equals to DEFAULT_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE
        defaultCampioneBiologicoShouldBeFound("terapiaAntiandrogenicaNeoadiuvante.equals=" + DEFAULT_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE);

        // Get all the campioneBiologicoList where terapiaAntiandrogenicaNeoadiuvante equals to UPDATED_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE
        defaultCampioneBiologicoShouldNotBeFound("terapiaAntiandrogenicaNeoadiuvante.equals=" + UPDATED_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTerapiaAntiandrogenicaNeoadiuvanteIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where terapiaAntiandrogenicaNeoadiuvante in DEFAULT_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE or UPDATED_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE
        defaultCampioneBiologicoShouldBeFound("terapiaAntiandrogenicaNeoadiuvante.in=" + DEFAULT_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE + "," + UPDATED_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE);

        // Get all the campioneBiologicoList where terapiaAntiandrogenicaNeoadiuvante equals to UPDATED_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE
        defaultCampioneBiologicoShouldNotBeFound("terapiaAntiandrogenicaNeoadiuvante.in=" + UPDATED_TERAPIA_ANTIANDROGENICA_NEOADIUVANTE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByTerapiaAntiandrogenicaNeoadiuvanteIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where terapiaAntiandrogenicaNeoadiuvante is not null
        defaultCampioneBiologicoShouldBeFound("terapiaAntiandrogenicaNeoadiuvante.specified=true");

        // Get all the campioneBiologicoList where terapiaAntiandrogenicaNeoadiuvante is null
        defaultCampioneBiologicoShouldNotBeFound("terapiaAntiandrogenicaNeoadiuvante.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByRadioterapiaPelviIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where radioterapiaPelvi equals to DEFAULT_RADIOTERAPIA_PELVI
        defaultCampioneBiologicoShouldBeFound("radioterapiaPelvi.equals=" + DEFAULT_RADIOTERAPIA_PELVI);

        // Get all the campioneBiologicoList where radioterapiaPelvi equals to UPDATED_RADIOTERAPIA_PELVI
        defaultCampioneBiologicoShouldNotBeFound("radioterapiaPelvi.equals=" + UPDATED_RADIOTERAPIA_PELVI);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByRadioterapiaPelviIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where radioterapiaPelvi in DEFAULT_RADIOTERAPIA_PELVI or UPDATED_RADIOTERAPIA_PELVI
        defaultCampioneBiologicoShouldBeFound("radioterapiaPelvi.in=" + DEFAULT_RADIOTERAPIA_PELVI + "," + UPDATED_RADIOTERAPIA_PELVI);

        // Get all the campioneBiologicoList where radioterapiaPelvi equals to UPDATED_RADIOTERAPIA_PELVI
        defaultCampioneBiologicoShouldNotBeFound("radioterapiaPelvi.in=" + UPDATED_RADIOTERAPIA_PELVI);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByRadioterapiaPelviIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where radioterapiaPelvi is not null
        defaultCampioneBiologicoShouldBeFound("radioterapiaPelvi.specified=true");

        // Get all the campioneBiologicoList where radioterapiaPelvi is null
        defaultCampioneBiologicoShouldNotBeFound("radioterapiaPelvi.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByRischioIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where rischio equals to DEFAULT_RISCHIO
        defaultCampioneBiologicoShouldBeFound("rischio.equals=" + DEFAULT_RISCHIO);

        // Get all the campioneBiologicoList where rischio equals to UPDATED_RISCHIO
        defaultCampioneBiologicoShouldNotBeFound("rischio.equals=" + UPDATED_RISCHIO);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByRischioIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where rischio in DEFAULT_RISCHIO or UPDATED_RISCHIO
        defaultCampioneBiologicoShouldBeFound("rischio.in=" + DEFAULT_RISCHIO + "," + UPDATED_RISCHIO);

        // Get all the campioneBiologicoList where rischio equals to UPDATED_RISCHIO
        defaultCampioneBiologicoShouldNotBeFound("rischio.in=" + UPDATED_RISCHIO);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByRischioIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where rischio is not null
        defaultCampioneBiologicoShouldBeFound("rischio.specified=true");

        // Get all the campioneBiologicoList where rischio is null
        defaultCampioneBiologicoShouldNotBeFound("rischio.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataInterventoIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataIntervento equals to DEFAULT_DATA_INTERVENTO
        defaultCampioneBiologicoShouldBeFound("dataIntervento.equals=" + DEFAULT_DATA_INTERVENTO);

        // Get all the campioneBiologicoList where dataIntervento equals to UPDATED_DATA_INTERVENTO
        defaultCampioneBiologicoShouldNotBeFound("dataIntervento.equals=" + UPDATED_DATA_INTERVENTO);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataInterventoIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataIntervento in DEFAULT_DATA_INTERVENTO or UPDATED_DATA_INTERVENTO
        defaultCampioneBiologicoShouldBeFound("dataIntervento.in=" + DEFAULT_DATA_INTERVENTO + "," + UPDATED_DATA_INTERVENTO);

        // Get all the campioneBiologicoList where dataIntervento equals to UPDATED_DATA_INTERVENTO
        defaultCampioneBiologicoShouldNotBeFound("dataIntervento.in=" + UPDATED_DATA_INTERVENTO);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataInterventoIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataIntervento is not null
        defaultCampioneBiologicoShouldBeFound("dataIntervento.specified=true");

        // Get all the campioneBiologicoList where dataIntervento is null
        defaultCampioneBiologicoShouldNotBeFound("dataIntervento.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataInterventoIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataIntervento greater than or equals to DEFAULT_DATA_INTERVENTO
        defaultCampioneBiologicoShouldBeFound("dataIntervento.greaterOrEqualThan=" + DEFAULT_DATA_INTERVENTO);

        // Get all the campioneBiologicoList where dataIntervento greater than or equals to UPDATED_DATA_INTERVENTO
        defaultCampioneBiologicoShouldNotBeFound("dataIntervento.greaterOrEqualThan=" + UPDATED_DATA_INTERVENTO);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByDataInterventoIsLessThanSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where dataIntervento less than or equals to DEFAULT_DATA_INTERVENTO
        defaultCampioneBiologicoShouldNotBeFound("dataIntervento.lessThan=" + DEFAULT_DATA_INTERVENTO);

        // Get all the campioneBiologicoList where dataIntervento less than or equals to UPDATED_DATA_INTERVENTO
        defaultCampioneBiologicoShouldBeFound("dataIntervento.lessThan=" + UPDATED_DATA_INTERVENTO);
    }


    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoTIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoT equals to DEFAULT_ESAME_ISTOLOGICO_T
        defaultCampioneBiologicoShouldBeFound("esameIstologicoT.equals=" + DEFAULT_ESAME_ISTOLOGICO_T);

        // Get all the campioneBiologicoList where esameIstologicoT equals to UPDATED_ESAME_ISTOLOGICO_T
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoT.equals=" + UPDATED_ESAME_ISTOLOGICO_T);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoTIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoT in DEFAULT_ESAME_ISTOLOGICO_T or UPDATED_ESAME_ISTOLOGICO_T
        defaultCampioneBiologicoShouldBeFound("esameIstologicoT.in=" + DEFAULT_ESAME_ISTOLOGICO_T + "," + UPDATED_ESAME_ISTOLOGICO_T);

        // Get all the campioneBiologicoList where esameIstologicoT equals to UPDATED_ESAME_ISTOLOGICO_T
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoT.in=" + UPDATED_ESAME_ISTOLOGICO_T);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoTIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoT is not null
        defaultCampioneBiologicoShouldBeFound("esameIstologicoT.specified=true");

        // Get all the campioneBiologicoList where esameIstologicoT is null
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoT.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoNIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoN equals to DEFAULT_ESAME_ISTOLOGICO_N
        defaultCampioneBiologicoShouldBeFound("esameIstologicoN.equals=" + DEFAULT_ESAME_ISTOLOGICO_N);

        // Get all the campioneBiologicoList where esameIstologicoN equals to UPDATED_ESAME_ISTOLOGICO_N
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoN.equals=" + UPDATED_ESAME_ISTOLOGICO_N);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoNIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoN in DEFAULT_ESAME_ISTOLOGICO_N or UPDATED_ESAME_ISTOLOGICO_N
        defaultCampioneBiologicoShouldBeFound("esameIstologicoN.in=" + DEFAULT_ESAME_ISTOLOGICO_N + "," + UPDATED_ESAME_ISTOLOGICO_N);

        // Get all the campioneBiologicoList where esameIstologicoN equals to UPDATED_ESAME_ISTOLOGICO_N
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoN.in=" + UPDATED_ESAME_ISTOLOGICO_N);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoNIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoN is not null
        defaultCampioneBiologicoShouldBeFound("esameIstologicoN.specified=true");

        // Get all the campioneBiologicoList where esameIstologicoN is null
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoN.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoGleasonScoreComposizioneIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScoreComposizione equals to DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE
        defaultCampioneBiologicoShouldBeFound("esameIstologicoGleasonScoreComposizione.equals=" + DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScoreComposizione equals to UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoGleasonScoreComposizione.equals=" + UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoGleasonScoreComposizioneIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScoreComposizione in DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE or UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE
        defaultCampioneBiologicoShouldBeFound("esameIstologicoGleasonScoreComposizione.in=" + DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE + "," + UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScoreComposizione equals to UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoGleasonScoreComposizione.in=" + UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE_COMPOSIZIONE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoGleasonScoreComposizioneIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScoreComposizione is not null
        defaultCampioneBiologicoShouldBeFound("esameIstologicoGleasonScoreComposizione.specified=true");

        // Get all the campioneBiologicoList where esameIstologicoGleasonScoreComposizione is null
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoGleasonScoreComposizione.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoGleasonScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore equals to DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE
        defaultCampioneBiologicoShouldBeFound("esameIstologicoGleasonScore.equals=" + DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore equals to UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoGleasonScore.equals=" + UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoGleasonScoreIsInShouldWork() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore in DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE or UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE
        defaultCampioneBiologicoShouldBeFound("esameIstologicoGleasonScore.in=" + DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE + "," + UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore equals to UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoGleasonScore.in=" + UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoGleasonScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore is not null
        defaultCampioneBiologicoShouldBeFound("esameIstologicoGleasonScore.specified=true");

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore is null
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoGleasonScore.specified=false");
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoGleasonScoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore greater than or equals to DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE
        defaultCampioneBiologicoShouldBeFound("esameIstologicoGleasonScore.greaterOrEqualThan=" + DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore greater than or equals to UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoGleasonScore.greaterOrEqualThan=" + UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE);
    }

    @Test
    @Transactional
    public void getAllCampioneBiologicosByEsameIstologicoGleasonScoreIsLessThanSomething() throws Exception {
        // Initialize the database
        campioneBiologicoRepository.saveAndFlush(campioneBiologico);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore less than or equals to DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE
        defaultCampioneBiologicoShouldNotBeFound("esameIstologicoGleasonScore.lessThan=" + DEFAULT_ESAME_ISTOLOGICO_GLEASON_SCORE);

        // Get all the campioneBiologicoList where esameIstologicoGleasonScore less than or equals to UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE
        defaultCampioneBiologicoShouldBeFound("esameIstologicoGleasonScore.lessThan=" + UPDATED_ESAME_ISTOLOGICO_GLEASON_SCORE);
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultCampioneBiologicoShouldBeFound(String filter) throws Exception {
        restCampioneBiologicoMockMvc.perform(get("/api/campione-biologicos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campioneBiologico.getId().intValue())))
            .andExpect(jsonPath("$.[*].codRH").value(hasItem(DEFAULT_COD_RH.toString())))
            .andExpect(jsonPath("$.[*].numeroCartellaClinica").value(hasItem(DEFAULT_NUMERO_CARTELLA_CLINICA.intValue())))
            .andExpect(jsonPath("$.[*].codUMG").value(hasItem(DEFAULT_COD_UMG.toString())))
            .andExpect(jsonPath("$.[*].dataReclutament").value(hasItem(DEFAULT_DATA_RECLUTAMENT.toString())))
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

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultCampioneBiologicoShouldNotBeFound(String filter) throws Exception {
        restCampioneBiologicoMockMvc.perform(get("/api/campione-biologicos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
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
            .dataReclutament(UPDATED_DATA_RECLUTAMENT)
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
        assertThat(testCampioneBiologico.getDataReclutament()).isEqualTo(UPDATED_DATA_RECLUTAMENT);
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
