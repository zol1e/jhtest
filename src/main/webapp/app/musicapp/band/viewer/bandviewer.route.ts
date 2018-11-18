import { Route } from '@angular/router';
import { BandlistComponent } from 'app/musicapp/band/bandlist.component';
import { BandviewerComponent } from 'app/musicapp/band/viewer/bandviewer.component';
import { BandinfoComponent } from 'app/musicapp/band/bandinfo.component';
import { MusicianlistComponent } from 'app/musicapp/musician/musicianlist.component';

export const bandViewerRoute: Route = {
    path: 'bandviewer',
    component: BandviewerComponent,
    children: [
        { path: 'bandinfo', component: BandinfoComponent, outlet: 'bandInfoHolder' },
        { path: 'musicianlist', component: MusicianlistComponent, outlet: 'musiciansHolder' }
    ]
};
