import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMusician } from 'app/shared/model/musician.model';

@Component({
    selector: 'jhi-musician-detail',
    templateUrl: './musician-detail.component.html'
})
export class MusicianDetailComponent implements OnInit {
    musician: IMusician;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ musician }) => {
            this.musician = musician;
        });
    }

    previousState() {
        window.history.back();
    }
}
