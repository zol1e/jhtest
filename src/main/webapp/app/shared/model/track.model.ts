import { Moment } from 'moment';
import { IMusician } from 'app/shared/model//musician.model';

export interface ITrack {
    id?: number;
    title?: string;
    year?: Moment;
    bandId?: number;
    performers?: IMusician[];
}

export class Track implements ITrack {
    constructor(public id?: number, public title?: string, public year?: Moment, public bandId?: number, public performers?: IMusician[]) {}
}
