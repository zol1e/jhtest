import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Band } from 'app/shared/model/band.model';
import { BandService } from './band.service';
import { BandComponent } from './band.component';
import { BandDetailComponent } from './band-detail.component';
import { BandUpdateComponent } from './band-update.component';
import { BandDeletePopupComponent } from './band-delete-dialog.component';
import { IBand } from 'app/shared/model/band.model';

@Injectable({ providedIn: 'root' })
export class BandResolve implements Resolve<IBand> {
    constructor(private service: BandService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((band: HttpResponse<Band>) => band.body));
        }
        return of(new Band());
    }
}

export const bandRoute: Routes = [
    {
        path: 'band',
        component: BandComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Bands'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'band/:id/view',
        component: BandDetailComponent,
        resolve: {
            band: BandResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Bands'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'band/new',
        component: BandUpdateComponent,
        resolve: {
            band: BandResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Bands'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'band/:id/edit',
        component: BandUpdateComponent,
        resolve: {
            band: BandResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Bands'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bandPopupRoute: Routes = [
    {
        path: 'band/:id/delete',
        component: BandDeletePopupComponent,
        resolve: {
            band: BandResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Bands'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
