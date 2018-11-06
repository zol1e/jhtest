import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IBand } from 'app/shared/model/band.model';
import { Principal } from 'app/core';
import { BandService } from './band.service';

@Component({
    selector: 'jhi-band',
    templateUrl: './band.component.html'
})
export class BandComponent implements OnInit, OnDestroy {
    bands: IBand[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private bandService: BandService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.bandService.query().subscribe(
            (res: HttpResponse<IBand[]>) => {
                this.bands = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInBands();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IBand) {
        return item.id;
    }

    registerChangeInBands() {
        this.eventSubscriber = this.eventManager.subscribe('bandListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
