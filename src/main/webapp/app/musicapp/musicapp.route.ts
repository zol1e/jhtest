import { Route } from '@angular/router';
import { MusicAppComponent } from 'app/musicapp/musicapp.component';
import { BandlistComponent } from 'app/musicapp/band/bandlist.component';
import { BandinfoComponent } from 'app/musicapp/band/bandinfo.component';

export const musicappRoute: Route = {
    path: 'musicapp',
    component: MusicAppComponent,
    children: [
        { path: 'bandlist', component: BandlistComponent, outlet: 'bandListHolder' },
        { path: 'bandinfo', component: BandinfoComponent, outlet: 'contentHolder' }
    ]
};
