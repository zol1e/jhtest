import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhtestSharedModule } from 'app/shared';
import {
    MusicianComponent,
    MusicianDetailComponent,
    MusicianUpdateComponent,
    MusicianDeletePopupComponent,
    MusicianDeleteDialogComponent,
    musicianRoute,
    musicianPopupRoute
} from './';

const ENTITY_STATES = [...musicianRoute, ...musicianPopupRoute];

@NgModule({
    imports: [JhtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        MusicianComponent,
        MusicianDetailComponent,
        MusicianUpdateComponent,
        MusicianDeleteDialogComponent,
        MusicianDeletePopupComponent
    ],
    entryComponents: [MusicianComponent, MusicianUpdateComponent, MusicianDeleteDialogComponent, MusicianDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhtestMusicianModule {}
