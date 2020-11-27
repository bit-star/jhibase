import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMicroAppGroupMp } from 'app/shared/model/micro-app-group-mp.model';

type EntityResponseType = HttpResponse<IMicroAppGroupMp>;
type EntityArrayResponseType = HttpResponse<IMicroAppGroupMp[]>;

@Injectable({ providedIn: 'root' })
export class MicroAppGroupMpService {
  public resourceUrl = SERVER_API_URL + 'api/micro-app-groups';

  constructor(protected http: HttpClient) {}

  create(microAppGroup: IMicroAppGroupMp): Observable<EntityResponseType> {
    return this.http.post<IMicroAppGroupMp>(this.resourceUrl, microAppGroup, { observe: 'response' });
  }

  update(microAppGroup: IMicroAppGroupMp): Observable<EntityResponseType> {
    return this.http.put<IMicroAppGroupMp>(this.resourceUrl, microAppGroup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMicroAppGroupMp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMicroAppGroupMp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
