import { Route } from '@angular/router';
import { MusicAppComponent } from 'app/musicapp/musicapp.component';
import { BandlistComponent } from 'app/musicapp/band/bandlist.component';

export const bandlistRoute: Route = {
    path: 'bandlist',
    component: BandlistComponent
};
