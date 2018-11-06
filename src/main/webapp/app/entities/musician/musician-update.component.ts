import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IMusician } from 'app/shared/model/musician.model';
import { MusicianService } from './musician.service';
import { ITrack } from 'app/shared/model/track.model';
import { TrackService } from 'app/entities/track';
import { IBand } from 'app/shared/model/band.model';
import { BandService } from 'app/entities/band';

@Component({
    selector: 'jhi-musician-update',
    templateUrl: './musician-update.component.html'
})
export class MusicianUpdateComponent implements OnInit {
    private _musician: IMusician;
    isSaving: boolean;

    tracks: ITrack[];

    bands: IBand[];
    birthdayDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private musicianService: MusicianService,
        private trackService: TrackService,
        private bandService: BandService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ musician }) => {
            this.musician = musician;
        });
        this.trackService.query().subscribe(
            (res: HttpResponse<ITrack[]>) => {
                this.tracks = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.bandService.query().subscribe(
            (res: HttpResponse<IBand[]>) => {
                this.bands = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.musician.id !== undefined) {
            this.subscribeToSaveResponse(this.musicianService.update(this.musician));
        } else {
            this.subscribeToSaveResponse(this.musicianService.create(this.musician));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IMusician>>) {
        result.subscribe((res: HttpResponse<IMusician>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackTrackById(index: number, item: ITrack) {
        return item.id;
    }

    trackBandById(index: number, item: IBand) {
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
    get musician() {
        return this._musician;
    }

    set musician(musician: IMusician) {
        this._musician = musician;
    }
}
