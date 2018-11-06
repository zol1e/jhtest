import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITrack } from 'app/shared/model/track.model';

type EntityResponseType = HttpResponse<ITrack>;
type EntityArrayResponseType = HttpResponse<ITrack[]>;

@Injectable({ providedIn: 'root' })
export class TrackService {
    private resourceUrl = SERVER_API_URL + 'api/tracks';

    constructor(private http: HttpClient) {}

    create(track: ITrack): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(track);
        return this.http
            .post<ITrack>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(track: ITrack): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(track);
        return this.http
            .put<ITrack>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITrack>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITrack[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(track: ITrack): ITrack {
        const copy: ITrack = Object.assign({}, track, {
            year: track.year != null && track.year.isValid() ? track.year.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.year = res.body.year != null ? moment(res.body.year) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((track: ITrack) => {
            track.year = track.year != null ? moment(track.year) : null;
        });
        return res;
    }
}
