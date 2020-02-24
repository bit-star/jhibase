import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';

type EntityResponseType = HttpResponse<IFmpSubCompanyMp>;
type EntityArrayResponseType = HttpResponse<IFmpSubCompanyMp[]>;

@Injectable({ providedIn: 'root' })
export class FmpSubCompanyMpService {
  public resourceUrl = SERVER_API_URL + 'api/fmp-sub-companies';

  constructor(protected http: HttpClient) {}

  create(fmpSubCompany: IFmpSubCompanyMp): Observable<EntityResponseType> {
    return this.http.post<IFmpSubCompanyMp>(this.resourceUrl, fmpSubCompany, { observe: 'response' });
  }

  update(fmpSubCompany: IFmpSubCompanyMp): Observable<EntityResponseType> {
    return this.http.put<IFmpSubCompanyMp>(this.resourceUrl, fmpSubCompany, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IFmpSubCompanyMp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IFmpSubCompanyMp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
