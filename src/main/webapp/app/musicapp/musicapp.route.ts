import { Route } from '@angular/router';
import { MusicAppComponent } from 'app/musicapp/musicapp.component';
import { BandlistComponent } from 'app/musicapp/band/bandlist.component';

export const musicappRoute: Route = {
    path: 'musicapp',
    component: MusicAppComponent,
    children: [{ path: 'bandlist', component: BandlistComponent }]
};
