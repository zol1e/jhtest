import { Route } from '@angular/router';
import { MusicAppComponent } from 'app/musicapp/musicapp.component';
import { BandlistComponent } from 'app/musicapp/band/bandlist.component';
import { AlbumlistComponent } from 'app/musicapp/album/albumlist.component';
import { BandviewerComponent } from 'app/musicapp/band/viewer/bandviewer.component';
import { BandinfoComponent } from 'app/musicapp/band/bandinfo.component';
import { MusicianlistComponent } from 'app/musicapp/musician/musicianlist.component';

export const musicappRoute: Route = {
    path: 'musicapp',
    component: MusicAppComponent,
    children: [
        { path: 'bandlist', component: BandlistComponent, outlet: 'bandListHolder' },
        { path: 'albumlist', component: AlbumlistComponent, outlet: 'albumListHolder' },
        {
            path: 'bandviewer',
            component: BandviewerComponent,
            outlet: 'contentHolder',
            children: [
                { path: 'bandinfo', component: BandinfoComponent, outlet: 'bandInfoHolder' },
                { path: 'musicianlist', component: MusicianlistComponent, outlet: 'musiciansHolder' }
            ]
        }
    ]
};
