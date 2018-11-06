import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAlbum } from 'app/shared/model/album.model';
import { AlbumService } from './album.service';
import { IBand } from 'app/shared/model/band.model';
import { BandService } from 'app/entities/band';

@Component({
    selector: 'jhi-album-update',
    templateUrl: './album-update.component.html'
})
export class AlbumUpdateComponent implements OnInit {
    private _album: IAlbum;
    isSaving: boolean;

    bands: IBand[];
    yearDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private albumService: AlbumService,
        private bandService: BandService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ album }) => {
            this.album = album;
        });
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
        if (this.album.id !== undefined) {
            this.subscribeToSaveResponse(this.albumService.update(this.album));
        } else {
            this.subscribeToSaveResponse(this.albumService.create(this.album));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAlbum>>) {
        result.subscribe((res: HttpResponse<IAlbum>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get album() {
        return this._album;
    }

    set album(album: IAlbum) {
        this._album = album;
    }
}
