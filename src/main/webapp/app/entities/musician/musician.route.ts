import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Musician } from 'app/shared/model/musician.model';
import { MusicianService } from './musician.service';
import { MusicianComponent } from './musician.component';
import { MusicianDetailComponent } from './musician-detail.component';
import { MusicianUpdateComponent } from './musician-update.component';
import { MusicianDeletePopupComponent } from './musician-delete-dialog.component';
import { IMusician } from 'app/shared/model/musician.model';

@Injectable({ providedIn: 'root' })
export class MusicianResolve implements Resolve<IMusician> {
    constructor(private service: MusicianService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((musician: HttpResponse<Musician>) => musician.body));
        }
        return of(new Musician());
    }
}

export const musicianRoute: Routes = [
    {
        path: 'musician',
        component: MusicianComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Musicians'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'musician/:id/view',
        component: MusicianDetailComponent,
        resolve: {
            musician: MusicianResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Musicians'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'musician/new',
        component: MusicianUpdateComponent,
        resolve: {
            musician: MusicianResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Musicians'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'musician/:id/edit',
        component: MusicianUpdateComponent,
        resolve: {
            musician: MusicianResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Musicians'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const musicianPopupRoute: Routes = [
    {
        path: 'musician/:id/delete',
        component: MusicianDeletePopupComponent,
        resolve: {
            musician: MusicianResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Musicians'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
