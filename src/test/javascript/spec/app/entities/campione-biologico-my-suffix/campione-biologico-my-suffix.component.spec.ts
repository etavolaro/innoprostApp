/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InnoprostAppTestModule } from '../../../test.module';
import { CampioneBiologicoMySuffixComponent } from 'app/entities/campione-biologico-my-suffix/campione-biologico-my-suffix.component';
import { CampioneBiologicoMySuffixService } from 'app/entities/campione-biologico-my-suffix/campione-biologico-my-suffix.service';
import { CampioneBiologicoMySuffix } from 'app/shared/model/campione-biologico-my-suffix.model';

describe('Component Tests', () => {
    describe('CampioneBiologicoMySuffix Management Component', () => {
        let comp: CampioneBiologicoMySuffixComponent;
        let fixture: ComponentFixture<CampioneBiologicoMySuffixComponent>;
        let service: CampioneBiologicoMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InnoprostAppTestModule],
                declarations: [CampioneBiologicoMySuffixComponent],
                providers: []
            })
                .overrideTemplate(CampioneBiologicoMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CampioneBiologicoMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CampioneBiologicoMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CampioneBiologicoMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.campioneBiologicos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
