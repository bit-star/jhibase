import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILocationFlat } from 'app/shared/model/location-flat.model';

type EntityResponseType = HttpResponse<ILocationFlat>;
type EntityArrayResponseType = HttpResponse<ILocationFlat[]>;

@Injectable({ providedIn: 'root' })
export class LocationFlatService {
  public resourceUrl = SERVER_API_URL + 'api/location-flats';

  constructor(protected http: HttpClient) {}

  create(locationFlat: ILocationFlat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(locationFlat);
    return this.http
      .post<ILocationFlat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(locationFlat: ILocationFlat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(locationFlat);
    return this.http
      .put<ILocationFlat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILocationFlat>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILocationFlat[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(locationFlat: ILocationFlat): ILocationFlat {
    const copy: ILocationFlat = Object.assign({}, locationFlat, {
      createdDate: locationFlat.createdDate && locationFlat.createdDate.isValid() ? locationFlat.createdDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((locationFlat: ILocationFlat) => {
        locationFlat.createdDate = locationFlat.createdDate ? moment(locationFlat.createdDate) : undefined;
      });
    }
    return res;
  }
}
