import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IAlbum } from 'app/shared/model/album.model';
import { Principal } from 'app/core';
import { AlbumService } from './album.service';

@Component({
    selector: 'jhi-album',
    templateUrl: './album.component.html'
})
export class AlbumComponent implements OnInit, OnDestroy {
    albums: IAlbum[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private albumService: AlbumService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.albumService.query().subscribe(
            (res: HttpResponse<IAlbum[]>) => {
                this.albums = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInAlbums();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IAlbum) {
        return item.id;
    }

    registerChangeInAlbums() {
        this.eventSubscriber = this.eventManager.subscribe('albumListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
