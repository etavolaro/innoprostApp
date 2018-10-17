import { Moment } from 'moment';

export const enum TipoCampione {
    EMATICO = 'EMATICO',
    URINE = 'URINE',
    EMATICO_URINE = 'EMATICO_URINE'
}

export const enum TipoMalattia {
    PCA = 'PCA',
    IPB = 'IPB'
}

export const enum TipoRischio {
    BASSO = 'BASSO',
    MEDIO = 'MEDIO',
    ALTO = 'ALTO',
    INTERMEDIO = 'INTERMEDIO'
}

export interface ICampioneBiologicoMySuffix {
    id?: number;
    codRH?: string;
    numeroCartellaClinica?: number;
    codUMG?: string;
    dataReclutament?: Moment;
    etaPaziente?: number;
    dimensioneGhiandolaProstatica?: number;
    tipoCampione?: TipoCampione;
    valutazionePCA3?: string;
    psaTotale?: number;
    rapportoFT?: number;
    psaFree?: number;
    malattia?: TipoMalattia;
    dataBiopsia?: Moment;
    esitoBiopsiaProstaticaGleasonScoreComposizione?: string;
    esitoBiopsiaProstaticaGleasonScore?: string;
    numeroPrelieviPositiviSulTotale?: number;
    totalePrelievi?: number;
    pregressaChirurgiaProstatica?: boolean;
    terapiaInibitori5AlfaReduttasi?: boolean;
    terapiaAntiandrogenicaNeoadiuvante?: boolean;
    radioterapiaPelvi?: boolean;
    rischio?: TipoRischio;
    dataIntervento?: Moment;
    esameIstologicoT?: string;
    esameIstologicoN?: string;
    esameIstologicoGleasonScoreComposizione?: string;
    esameIstologicoGleasonScore?: number;
}

export class CampioneBiologicoMySuffix implements ICampioneBiologicoMySuffix {
    constructor(
        public id?: number,
        public codRH?: string,
        public numeroCartellaClinica?: number,
        public codUMG?: string,
        public dataReclutament?: Moment,
        public etaPaziente?: number,
        public dimensioneGhiandolaProstatica?: number,
        public tipoCampione?: TipoCampione,
        public valutazionePCA3?: string,
        public psaTotale?: number,
        public rapportoFT?: number,
        public psaFree?: number,
        public malattia?: TipoMalattia,
        public dataBiopsia?: Moment,
        public esitoBiopsiaProstaticaGleasonScoreComposizione?: string,
        public esitoBiopsiaProstaticaGleasonScore?: string,
        public numeroPrelieviPositiviSulTotale?: number,
        public totalePrelievi?: number,
        public pregressaChirurgiaProstatica?: boolean,
        public terapiaInibitori5AlfaReduttasi?: boolean,
        public terapiaAntiandrogenicaNeoadiuvante?: boolean,
        public radioterapiaPelvi?: boolean,
        public rischio?: TipoRischio,
        public dataIntervento?: Moment,
        public esameIstologicoT?: string,
        public esameIstologicoN?: string,
        public esameIstologicoGleasonScoreComposizione?: string,
        public esameIstologicoGleasonScore?: number
    ) {
        this.pregressaChirurgiaProstatica = false;
        this.terapiaInibitori5AlfaReduttasi = false;
        this.terapiaAntiandrogenicaNeoadiuvante = false;
        this.radioterapiaPelvi = false;
    }
}
