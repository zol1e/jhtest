import { Moment } from 'moment';

export interface IAlbum {
    id?: number;
    name?: string;
    year?: Moment;
    bandId?: number;
}

export class Album implements IAlbum {
    constructor(public id?: number, public name?: string, public year?: Moment, public bandId?: number) {}
}
