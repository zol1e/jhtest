import { Route } from '@angular/router';
import { BandlistComponent } from 'app/musicapp/band/bandlist.component';
import { BandviewerComponent } from 'app/musicapp/band/viewer/bandviewer.component';

export const bandViewerRoute: Route = {
    path: 'bandviewer',
    component: BandviewerComponent
};
