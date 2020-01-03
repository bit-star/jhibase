import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ILocationVM } from 'app/shared/model/location-vm.model';

type EntityResponseType = HttpResponse<ILocationVM>;
type EntityArrayResponseType = HttpResponse<ILocationVM[]>;

@Injectable({ providedIn: 'root' })
export class LocationVMService {
  public resourceUrl = SERVER_API_URL + 'api/location-vms';

  constructor(protected http: HttpClient) {}

  create(locationVM: ILocationVM): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(locationVM);
    return this.http
      .post<ILocationVM>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(locationVM: ILocationVM): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(locationVM);
    return this.http
      .put<ILocationVM>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ILocationVM>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ILocationVM[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(locationVM: ILocationVM): ILocationVM {
    const copy: ILocationVM = Object.assign({}, locationVM, {
      createdDate: locationVM.createdDate && locationVM.createdDate.isValid() ? locationVM.createdDate.toJSON() : undefined
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
      res.body.forEach((locationVM: ILocationVM) => {
        locationVM.createdDate = locationVM.createdDate ? moment(locationVM.createdDate) : undefined;
      });
    }
    return res;
  }
}
