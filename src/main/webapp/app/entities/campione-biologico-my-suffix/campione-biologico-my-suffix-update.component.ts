import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICampioneBiologicoMySuffix } from 'app/shared/model/campione-biologico-my-suffix.model';
import { CampioneBiologicoMySuffixService } from './campione-biologico-my-suffix.service';

@Component({
    selector: 'jhi-campione-biologico-my-suffix-update',
    templateUrl: './campione-biologico-my-suffix-update.component.html'
})
export class CampioneBiologicoMySuffixUpdateComponent implements OnInit {
    private _campioneBiologico: ICampioneBiologicoMySuffix;
    isSaving: boolean;
    dataReclutamentDp: any;
    dataBiopsiaDp: any;
    dataInterventoDp: any;

    constructor(private campioneBiologicoService: CampioneBiologicoMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ campioneBiologico }) => {
            this.campioneBiologico = campioneBiologico;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.campioneBiologico.id !== undefined) {
            this.subscribeToSaveResponse(this.campioneBiologicoService.update(this.campioneBiologico));
        } else {
            this.subscribeToSaveResponse(this.campioneBiologicoService.create(this.campioneBiologico));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICampioneBiologicoMySuffix>>) {
        result.subscribe(
            (res: HttpResponse<ICampioneBiologicoMySuffix>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get campioneBiologico() {
        return this._campioneBiologico;
    }

    set campioneBiologico(campioneBiologico: ICampioneBiologicoMySuffix) {
        this._campioneBiologico = campioneBiologico;
    }
}
