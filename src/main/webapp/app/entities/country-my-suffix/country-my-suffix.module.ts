import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InnoprostAppSharedModule } from 'app/shared';
import {
    CountryMySuffixComponent,
    CountryMySuffixDetailComponent,
    CountryMySuffixUpdateComponent,
    CountryMySuffixDeletePopupComponent,
    CountryMySuffixDeleteDialogComponent,
    countryRoute,
    countryPopupRoute
} from './';

const ENTITY_STATES = [...countryRoute, ...countryPopupRoute];

@NgModule({
    imports: [InnoprostAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CountryMySuffixComponent,
        CountryMySuffixDetailComponent,
        CountryMySuffixUpdateComponent,
        CountryMySuffixDeleteDialogComponent,
        CountryMySuffixDeletePopupComponent
    ],
    entryComponents: [
        CountryMySuffixComponent,
        CountryMySuffixUpdateComponent,
        CountryMySuffixDeleteDialogComponent,
        CountryMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnoprostAppCountryMySuffixModule {}
