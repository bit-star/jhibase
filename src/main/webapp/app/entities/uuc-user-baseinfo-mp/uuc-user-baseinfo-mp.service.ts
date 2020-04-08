import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';

type EntityResponseType = HttpResponse<IUucUserBaseinfoMp>;
type EntityArrayResponseType = HttpResponse<IUucUserBaseinfoMp[]>;

@Injectable({ providedIn: 'root' })
export class UucUserBaseinfoMpService {
  public resourceUrl = SERVER_API_URL + 'api/uuc-user-baseinfos';

  constructor(protected http: HttpClient) {}

  create(uucUserBaseinfo: IUucUserBaseinfoMp): Observable<EntityResponseType> {
    return this.http.post<IUucUserBaseinfoMp>(this.resourceUrl, uucUserBaseinfo, { observe: 'response' });
  }

  update(uucUserBaseinfo: IUucUserBaseinfoMp): Observable<EntityResponseType> {
    return this.http.put<IUucUserBaseinfoMp>(this.resourceUrl, uucUserBaseinfo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IUucUserBaseinfoMp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IUucUserBaseinfoMp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
