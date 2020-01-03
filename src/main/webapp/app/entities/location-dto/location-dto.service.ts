import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILocationDTO } from 'app/shared/model/location-dto.model';

type EntityResponseType = HttpResponse<ILocationDTO>;
type EntityArrayResponseType = HttpResponse<ILocationDTO[]>;

@Injectable({ providedIn: 'root' })
export class LocationDTOService {
  public resourceUrl = SERVER_API_URL + 'api/location-dtos';

  constructor(protected http: HttpClient) {}

  create(locationDTO: ILocationDTO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(locationDTO);
    return this.http
      .post<ILocationDTO>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(locationDTO: ILocationDTO): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(locationDTO);
    return this.http
      .put<ILocationDTO>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILocationDTO>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILocationDTO[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(locationDTO: ILocationDTO): ILocationDTO {
    const copy: ILocationDTO = Object.assign({}, locationDTO, {
      createdDate: locationDTO.createdDate && locationDTO.createdDate.isValid() ? locationDTO.createdDate.toJSON() : undefined
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
      res.body.forEach((locationDTO: ILocationDTO) => {
        locationDTO.createdDate = locationDTO.createdDate ? moment(locationDTO.createdDate) : undefined;
      });
    }
    return res;
  }
}
