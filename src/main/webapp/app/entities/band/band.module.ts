import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhtestSharedModule } from 'app/shared';
import {
    BandComponent,
    BandDetailComponent,
    BandUpdateComponent,
    BandDeletePopupComponent,
    BandDeleteDialogComponent,
    bandRoute,
    bandPopupRoute
} from './';

const ENTITY_STATES = [...bandRoute, ...bandPopupRoute];

@NgModule({
    imports: [JhtestSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [BandComponent, BandDetailComponent, BandUpdateComponent, BandDeleteDialogComponent, BandDeletePopupComponent],
    entryComponents: [BandComponent, BandUpdateComponent, BandDeleteDialogComponent, BandDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhtestBandModule {}
