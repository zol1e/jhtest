import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBand } from 'app/shared/model/band.model';

type EntityResponseType = HttpResponse<IBand>;
type EntityArrayResponseType = HttpResponse<IBand[]>;

@Injectable({ providedIn: 'root' })
export class BandService {
    private resourceUrl = SERVER_API_URL + 'api/bands';

    constructor(private http: HttpClient) {}

    create(band: IBand): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(band);
        return this.http
            .post<IBand>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(band: IBand): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(band);
        return this.http
            .put<IBand>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IBand>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IBand[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(band: IBand): IBand {
        const copy: IBand = Object.assign({}, band, {
            founded: band.founded != null && band.founded.isValid() ? band.founded.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.founded = res.body.founded != null ? moment(res.body.founded) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((band: IBand) => {
            band.founded = band.founded != null ? moment(band.founded) : null;
        });
        return res;
    }
}
