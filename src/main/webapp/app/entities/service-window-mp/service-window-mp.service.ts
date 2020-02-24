import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IServiceWindowMp } from 'app/shared/model/service-window-mp.model';

type EntityResponseType = HttpResponse<IServiceWindowMp>;
type EntityArrayResponseType = HttpResponse<IServiceWindowMp[]>;

@Injectable({ providedIn: 'root' })
export class ServiceWindowMpService {
  public resourceUrl = SERVER_API_URL + 'api/service-windows';

  constructor(protected http: HttpClient) {}

  create(serviceWindow: IServiceWindowMp): Observable<EntityResponseType> {
    return this.http.post<IServiceWindowMp>(this.resourceUrl, serviceWindow, { observe: 'response' });
  }

  update(serviceWindow: IServiceWindowMp): Observable<EntityResponseType> {
    return this.http.put<IServiceWindowMp>(this.resourceUrl, serviceWindow, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IServiceWindowMp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IServiceWindowMp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
