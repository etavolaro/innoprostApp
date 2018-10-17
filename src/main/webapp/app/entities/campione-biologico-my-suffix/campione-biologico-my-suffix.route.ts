import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { CampioneBiologicoMySuffix } from 'app/shared/model/campione-biologico-my-suffix.model';
import { CampioneBiologicoMySuffixService } from './campione-biologico-my-suffix.service';
import { CampioneBiologicoMySuffixComponent } from './campione-biologico-my-suffix.component';
import { CampioneBiologicoMySuffixDetailComponent } from './campione-biologico-my-suffix-detail.component';
import { CampioneBiologicoMySuffixUpdateComponent } from './campione-biologico-my-suffix-update.component';
import { CampioneBiologicoMySuffixDeletePopupComponent } from './campione-biologico-my-suffix-delete-dialog.component';
import { ICampioneBiologicoMySuffix } from 'app/shared/model/campione-biologico-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class CampioneBiologicoMySuffixResolve implements Resolve<ICampioneBiologicoMySuffix> {
    constructor(private service: CampioneBiologicoMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((campioneBiologico: HttpResponse<CampioneBiologicoMySuffix>) => campioneBiologico.body);
        }
        return Observable.of(new CampioneBiologicoMySuffix());
    }
}

export const campioneBiologicoRoute: Routes = [
    {
        path: 'campione-biologico-my-suffix',
        component: CampioneBiologicoMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'innoprostApp.campioneBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'campione-biologico-my-suffix/:id/view',
        component: CampioneBiologicoMySuffixDetailComponent,
        resolve: {
            campioneBiologico: CampioneBiologicoMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innoprostApp.campioneBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'campione-biologico-my-suffix/new',
        component: CampioneBiologicoMySuffixUpdateComponent,
        resolve: {
            campioneBiologico: CampioneBiologicoMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innoprostApp.campioneBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'campione-biologico-my-suffix/:id/edit',
        component: CampioneBiologicoMySuffixUpdateComponent,
        resolve: {
            campioneBiologico: CampioneBiologicoMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innoprostApp.campioneBiologico.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const campioneBiologicoPopupRoute: Routes = [
    {
        path: 'campione-biologico-my-suffix/:id/delete',
        component: CampioneBiologicoMySuffixDeletePopupComponent,
        resolve: {
            campioneBiologico: CampioneBiologicoMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'innoprostApp.campioneBiologico.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
