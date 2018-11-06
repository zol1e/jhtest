import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhtestArticleModule } from './article/article.module';
import { JhtestAlbumModule } from './album/album.module';
import { JhtestBandModule } from './band/band.module';
import { JhtestMusicianModule } from './musician/musician.module';
import { JhtestTrackModule } from './track/track.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhtestArticleModule,
        JhtestAlbumModule,
        JhtestBandModule,
        JhtestMusicianModule,
        JhtestTrackModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhtestEntityModule {}
