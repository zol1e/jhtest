import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITrack } from 'app/shared/model/track.model';
import { TrackService } from './track.service';
import { IBand } from 'app/shared/model/band.model';
import { BandService } from 'app/entities/band';
import { IMusician } from 'app/shared/model/musician.model';
import { MusicianService } from 'app/entities/musician';

@Component({
    selector: 'jhi-track-update',
    templateUrl: './track-update.component.html'
})
export class TrackUpdateComponent implements OnInit {
    private _track: ITrack;
    isSaving: boolean;

    bands: IBand[];

    musicians: IMusician[];
    yearDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private trackService: TrackService,
        private bandService: BandService,
        private musicianService: MusicianService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ track }) => {
            this.track = track;
        });
        this.bandService.query().subscribe(
            (res: HttpResponse<IBand[]>) => {
                this.bands = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.track.id !== undefined) {
            this.subscribeToSaveResponse(this.trackService.update(this.track));
        } else {
            this.subscribeToSaveResponse(this.trackService.create(this.track));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITrack>>) {
        result.subscribe((res: HttpResponse<ITrack>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBandById(index: number, item: IBand) {
        return item.id;
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
    get track() {
        return this._track;
    }

    set track(track: ITrack) {
        this._track = track;
    }
}
