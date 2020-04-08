import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPushSubjectMp } from 'app/shared/model/push-subject-mp.model';

type EntityResponseType = HttpResponse<IPushSubjectMp>;
type EntityArrayResponseType = HttpResponse<IPushSubjectMp[]>;

@Injectable({ providedIn: 'root' })
export class PushSubjectMpService {
  public resourceUrl = SERVER_API_URL + 'api/push-subjects';

  constructor(protected http: HttpClient) {}

  create(pushSubject: IPushSubjectMp): Observable<EntityResponseType> {
    return this.http.post<IPushSubjectMp>(this.resourceUrl, pushSubject, { observe: 'response' });
  }

  update(pushSubject: IPushSubjectMp): Observable<EntityResponseType> {
    return this.http.put<IPushSubjectMp>(this.resourceUrl, pushSubject, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPushSubjectMp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPushSubjectMp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
