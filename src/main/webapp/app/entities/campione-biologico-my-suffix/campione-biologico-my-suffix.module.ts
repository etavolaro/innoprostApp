import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InnoprostAppSharedModule } from 'app/shared';
import {
    CampioneBiologicoMySuffixComponent,
    CampioneBiologicoMySuffixDetailComponent,
    CampioneBiologicoMySuffixUpdateComponent,
    CampioneBiologicoMySuffixDeletePopupComponent,
    CampioneBiologicoMySuffixDeleteDialogComponent,
    campioneBiologicoRoute,
    campioneBiologicoPopupRoute
} from './';

const ENTITY_STATES = [...campioneBiologicoRoute, ...campioneBiologicoPopupRoute];

@NgModule({
    imports: [InnoprostAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CampioneBiologicoMySuffixComponent,
        CampioneBiologicoMySuffixDetailComponent,
        CampioneBiologicoMySuffixUpdateComponent,
        CampioneBiologicoMySuffixDeleteDialogComponent,
        CampioneBiologicoMySuffixDeletePopupComponent
    ],
    entryComponents: [
        CampioneBiologicoMySuffixComponent,
        CampioneBiologicoMySuffixUpdateComponent,
        CampioneBiologicoMySuffixDeleteDialogComponent,
        CampioneBiologicoMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnoprostAppCampioneBiologicoMySuffixModule {}
