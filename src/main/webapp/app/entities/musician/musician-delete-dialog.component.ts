import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMusician } from 'app/shared/model/musician.model';
import { MusicianService } from './musician.service';

@Component({
    selector: 'jhi-musician-delete-dialog',
    templateUrl: './musician-delete-dialog.component.html'
})
export class MusicianDeleteDialogComponent {
    musician: IMusician;

    constructor(private musicianService: MusicianService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.musicianService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'musicianListModification',
                content: 'Deleted an musician'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-musician-delete-popup',
    template: ''
})
export class MusicianDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ musician }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(MusicianDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.musician = musician;
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
