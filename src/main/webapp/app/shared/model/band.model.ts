import { Moment } from 'moment';
import { IMusician } from 'app/shared/model//musician.model';
import { IAlbum } from 'app/shared/model//album.model';
import { ITrack } from 'app/shared/model//track.model';

export interface IBand {
    id?: number;
    name?: string;
    founded?: Moment;
    members?: IMusician[];
    albums?: IAlbum[];
    tracks?: ITrack[];
}

export class Band implements IBand {
    constructor(
        public id?: number,
        public name?: string,
        public founded?: Moment,
        public members?: IMusician[],
        public albums?: IAlbum[],
        public tracks?: ITrack[]
    ) {}
}
