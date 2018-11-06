import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IBand } from 'app/shared/model/band.model';
import { BandService } from './band.service';
import { IMusician } from 'app/shared/model/musician.model';
import { MusicianService } from 'app/entities/musician';

@Component({
    selector: 'jhi-band-update',
    templateUrl: './band-update.component.html'
})
export class BandUpdateComponent implements OnInit {
    private _band: IBand;
    isSaving: boolean;

    musicians: IMusician[];
    foundedDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private bandService: BandService,
        private musicianService: MusicianService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ band }) => {
            this.band = band;
        });
        this.musicianService.query().subscribe(
            (res: HttpResponse<IMusician[]>) => {
                this.musicians = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.band.id !== undefined) {
            this.subscribeToSaveResponse(this.bandService.update(this.band));
        } else {
            this.subscribeToSaveResponse(this.bandService.create(this.band));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBand>>) {
        result.subscribe((res: HttpResponse<IBand>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackMusicianById(index: number, item: IMusician) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get band() {
        return this._band;
    }

    set band(band: IBand) {
        this._band = band;
    }
}
