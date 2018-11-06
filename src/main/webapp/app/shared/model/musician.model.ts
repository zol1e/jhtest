import { Moment } from 'moment';
import { ITrack } from 'app/shared/model//track.model';
import { IBand } from 'app/shared/model//band.model';

export interface IMusician {
    id?: number;
    name?: string;
    birthday?: Moment;
    tracks?: ITrack[];
    bands?: IBand[];
}

export class Musician implements IMusician {
    constructor(public id?: number, public name?: string, public birthday?: Moment, public tracks?: ITrack[], public bands?: IBand[]) {}
}
