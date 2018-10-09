import { Moment } from 'moment';

export interface IArticle {
    id?: number;
    title?: string;
    content?: string;
    date?: Moment;
    writerId?: number;
}

export class Article implements IArticle {
    constructor(public id?: number, public title?: string, public content?: string, public date?: Moment, public writerId?: number) {}
}
