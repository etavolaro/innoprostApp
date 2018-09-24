import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICampioneBiologicoMySuffix } from 'app/shared/model/campione-biologico-my-suffix.model';
import { Principal } from 'app/core';
import { CampioneBiologicoMySuffixService } from './campione-biologico-my-suffix.service';

@Component({
    selector: 'jhi-campione-biologico-my-suffix',
    templateUrl: './campione-biologico-my-suffix.component.html'
})
export class CampioneBiologicoMySuffixComponent implements OnInit, OnDestroy {
    campioneBiologicos: ICampioneBiologicoMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private campioneBiologicoService: CampioneBiologicoMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.campioneBiologicoService.query().subscribe(
            (res: HttpResponse<ICampioneBiologicoMySuffix[]>) => {
                this.campioneBiologicos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCampioneBiologicos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICampioneBiologicoMySuffix) {
        return item.id;
    }

    registerChangeInCampioneBiologicos() {
        this.eventSubscriber = this.eventManager.subscribe('campioneBiologicoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
