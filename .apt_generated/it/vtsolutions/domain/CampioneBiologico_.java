package it.vtsolutions.domain;

import it.vtsolutions.domain.enumeration.TipoCampione;
import it.vtsolutions.domain.enumeration.TipoMalattia;
import it.vtsolutions.domain.enumeration.TipoRischio;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CampioneBiologico.class)
public abstract class CampioneBiologico_ {

	public static volatile SingularAttribute<CampioneBiologico, Float> psaTotale;
	public static volatile SingularAttribute<CampioneBiologico, Boolean> terapiaAntiandrogenicaNeoadiuvante;
	public static volatile SingularAttribute<CampioneBiologico, String> esameIstologicoGleasonScoreComposizione;
	public static volatile SingularAttribute<CampioneBiologico, Boolean> pregressaChirurgiaProstatica;
	public static volatile SingularAttribute<CampioneBiologico, String> esitoBiopsiaProstaticaGleasonScoreComposizione;
	public static volatile SingularAttribute<CampioneBiologico, String> codRH;
	public static volatile SingularAttribute<CampioneBiologico, Long> numeroCartellaClinica;
	public static volatile SingularAttribute<CampioneBiologico, String> esameIstologicoN;
	public static volatile SingularAttribute<CampioneBiologico, String> codUMG;
	public static volatile SingularAttribute<CampioneBiologico, Integer> dimensioneGhiandolaProstatica;
	public static volatile SingularAttribute<CampioneBiologico, LocalDate> dataBiopsia;
	public static volatile SingularAttribute<CampioneBiologico, TipoRischio> rischio;
	public static volatile SingularAttribute<CampioneBiologico, TipoCampione> tipoCampione;
	public static volatile SingularAttribute<CampioneBiologico, TipoMalattia> malattia;
	public static volatile SingularAttribute<CampioneBiologico, LocalDate> dataIntervento;
	public static volatile SingularAttribute<CampioneBiologico, String> esameIstologicoT;
	public static volatile SingularAttribute<CampioneBiologico, String> valutazionePCA3;
	public static volatile SingularAttribute<CampioneBiologico, Long> id;
	public static volatile SingularAttribute<CampioneBiologico, Integer> etaPaziente;
	public static volatile SingularAttribute<CampioneBiologico, Float> rapportoFT;
	public static volatile SingularAttribute<CampioneBiologico, String> esitoBiopsiaProstaticaGleasonScore;
	public static volatile SingularAttribute<CampioneBiologico, Integer> esameIstologicoGleasonScore;
	public static volatile SingularAttribute<CampioneBiologico, Float> psaFree;
	public static volatile SingularAttribute<CampioneBiologico, Integer> totalePrelievi;
	public static volatile SingularAttribute<CampioneBiologico, Boolean> radioterapiaPelvi;
	public static volatile SingularAttribute<CampioneBiologico, Integer> numeroPrelieviPositiviSulTotale;
	public static volatile SingularAttribute<CampioneBiologico, LocalDate> dataEsecuzione;
	public static volatile SingularAttribute<CampioneBiologico, Boolean> terapiaInibitori5AlfaReduttasi;

}

