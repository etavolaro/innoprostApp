/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InnoprostAppTestModule } from '../../../test.module';
import { CampioneBiologicoMySuffixDetailComponent } from 'app/entities/campione-biologico-my-suffix/campione-biologico-my-suffix-detail.component';
import { CampioneBiologicoMySuffix } from 'app/shared/model/campione-biologico-my-suffix.model';

describe('Component Tests', () => {
    describe('CampioneBiologicoMySuffix Management Detail Component', () => {
        let comp: CampioneBiologicoMySuffixDetailComponent;
        let fixture: ComponentFixture<CampioneBiologicoMySuffixDetailComponent>;
        const route = ({ data: of({ campioneBiologico: new CampioneBiologicoMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InnoprostAppTestModule],
                declarations: [CampioneBiologicoMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CampioneBiologicoMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CampioneBiologicoMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.campioneBiologico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
