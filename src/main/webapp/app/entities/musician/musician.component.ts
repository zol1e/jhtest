import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IMusician } from 'app/shared/model/musician.model';
import { Principal } from 'app/core';
import { MusicianService } from './musician.service';

@Component({
    selector: 'jhi-musician',
    templateUrl: './musician.component.html'
})
export class MusicianComponent implements OnInit, OnDestroy {
    musicians: IMusician[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private musicianService: MusicianService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.musicianService.query().subscribe(
            (res: HttpResponse<IMusician[]>) => {
                this.musicians = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInMusicians();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IMusician) {
        return item.id;
    }

    registerChangeInMusicians() {
        this.eventSubscriber = this.eventManager.subscribe('musicianListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
