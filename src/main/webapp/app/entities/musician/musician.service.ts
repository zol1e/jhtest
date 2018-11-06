import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IMusician } from 'app/shared/model/musician.model';

type EntityResponseType = HttpResponse<IMusician>;
type EntityArrayResponseType = HttpResponse<IMusician[]>;

@Injectable({ providedIn: 'root' })
export class MusicianService {
    private resourceUrl = SERVER_API_URL + 'api/musicians';

    constructor(private http: HttpClient) {}

    create(musician: IMusician): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(musician);
        return this.http
            .post<IMusician>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(musician: IMusician): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(musician);
        return this.http
            .put<IMusician>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IMusician>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IMusician[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(musician: IMusician): IMusician {
        const copy: IMusician = Object.assign({}, musician, {
            birthday: musician.birthday != null && musician.birthday.isValid() ? musician.birthday.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.birthday = res.body.birthday != null ? moment(res.body.birthday) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((musician: IMusician) => {
            musician.birthday = musician.birthday != null ? moment(musician.birthday) : null;
        });
        return res;
    }
}
