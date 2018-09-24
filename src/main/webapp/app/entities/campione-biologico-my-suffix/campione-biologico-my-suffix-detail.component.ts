import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICampioneBiologicoMySuffix } from 'app/shared/model/campione-biologico-my-suffix.model';

@Component({
    selector: 'jhi-campione-biologico-my-suffix-detail',
    templateUrl: './campione-biologico-my-suffix-detail.component.html'
})
export class CampioneBiologicoMySuffixDetailComponent implements OnInit {
    campioneBiologico: ICampioneBiologicoMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ campioneBiologico }) => {
            this.campioneBiologico = campioneBiologico;
        });
    }

    previousState() {
        window.history.back();
    }
}
