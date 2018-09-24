import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { InnoprostAppCampioneBiologicoMySuffixModule } from './campione-biologico-my-suffix/campione-biologico-my-suffix.module';
import { InnoprostAppRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { InnoprostAppCountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { InnoprostAppLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { InnoprostAppDepartmentMySuffixModule } from './department-my-suffix/department-my-suffix.module';
import { InnoprostAppTaskMySuffixModule } from './task-my-suffix/task-my-suffix.module';
import { InnoprostAppEmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { InnoprostAppJobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { InnoprostAppJobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        InnoprostAppCampioneBiologicoMySuffixModule,
        InnoprostAppRegionMySuffixModule,
        InnoprostAppCountryMySuffixModule,
        InnoprostAppLocationMySuffixModule,
        InnoprostAppDepartmentMySuffixModule,
        InnoprostAppTaskMySuffixModule,
        InnoprostAppEmployeeMySuffixModule,
        InnoprostAppJobMySuffixModule,
        InnoprostAppJobHistoryMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InnoprostAppEntityModule {}
