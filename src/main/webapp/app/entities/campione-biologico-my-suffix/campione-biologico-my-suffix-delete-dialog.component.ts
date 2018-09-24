import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICampioneBiologicoMySuffix } from 'app/shared/model/campione-biologico-my-suffix.model';
import { CampioneBiologicoMySuffixService } from './campione-biologico-my-suffix.service';

@Component({
    selector: 'jhi-campione-biologico-my-suffix-delete-dialog',
    templateUrl: './campione-biologico-my-suffix-delete-dialog.component.html'
})
export class CampioneBiologicoMySuffixDeleteDialogComponent {
    campioneBiologico: ICampioneBiologicoMySuffix;

    constructor(
        private campioneBiologicoService: CampioneBiologicoMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.campioneBiologicoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'campioneBiologicoListModification',
                content: 'Deleted an campioneBiologico'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-campione-biologico-my-suffix-delete-popup',
    template: ''
})
export class CampioneBiologicoMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ campioneBiologico }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CampioneBiologicoMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.campioneBiologico = campioneBiologico;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
