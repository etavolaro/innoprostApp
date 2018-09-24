import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICampioneBiologicoMySuffix } from 'app/shared/model/campione-biologico-my-suffix.model';

type EntityResponseType = HttpResponse<ICampioneBiologicoMySuffix>;
type EntityArrayResponseType = HttpResponse<ICampioneBiologicoMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class CampioneBiologicoMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/campione-biologicos';

    constructor(private http: HttpClient) {}

    create(campioneBiologico: ICampioneBiologicoMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(campioneBiologico);
        return this.http
            .post<ICampioneBiologicoMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(campioneBiologico: ICampioneBiologicoMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(campioneBiologico);
        return this.http
            .put<ICampioneBiologicoMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICampioneBiologicoMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICampioneBiologicoMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(campioneBiologico: ICampioneBiologicoMySuffix): ICampioneBiologicoMySuffix {
        const copy: ICampioneBiologicoMySuffix = Object.assign({}, campioneBiologico, {
            dataEsecuzione:
                campioneBiologico.dataEsecuzione != null && campioneBiologico.dataEsecuzione.isValid()
                    ? campioneBiologico.dataEsecuzione.format(DATE_FORMAT)
                    : null,
            dataBiopsia:
                campioneBiologico.dataBiopsia != null && campioneBiologico.dataBiopsia.isValid()
                    ? campioneBiologico.dataBiopsia.format(DATE_FORMAT)
                    : null,
            dataIntervento:
                campioneBiologico.dataIntervento != null && campioneBiologico.dataIntervento.isValid()
                    ? campioneBiologico.dataIntervento.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dataEsecuzione = res.body.dataEsecuzione != null ? moment(res.body.dataEsecuzione) : null;
        res.body.dataBiopsia = res.body.dataBiopsia != null ? moment(res.body.dataBiopsia) : null;
        res.body.dataIntervento = res.body.dataIntervento != null ? moment(res.body.dataIntervento) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((campioneBiologico: ICampioneBiologicoMySuffix) => {
            campioneBiologico.dataEsecuzione = campioneBiologico.dataEsecuzione != null ? moment(campioneBiologico.dataEsecuzione) : null;
            campioneBiologico.dataBiopsia = campioneBiologico.dataBiopsia != null ? moment(campioneBiologico.dataBiopsia) : null;
            campioneBiologico.dataIntervento = campioneBiologico.dataIntervento != null ? moment(campioneBiologico.dataIntervento) : null;
        });
        return res;
    }
}
