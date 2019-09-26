import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICspaceFile } from 'app/shared/model/cspace-file.model';

type EntityResponseType = HttpResponse<ICspaceFile>;
type EntityArrayResponseType = HttpResponse<ICspaceFile[]>;

@Injectable({ providedIn: 'root' })
export class CspaceFileService {
  public resourceUrl = SERVER_API_URL + 'api/cspace-files';

  constructor(protected http: HttpClient) {}

  create(cspaceFile: ICspaceFile): Observable<EntityResponseType> {
    return this.http.post<ICspaceFile>(this.resourceUrl, cspaceFile, { observe: 'response' });
  }

  update(cspaceFile: ICspaceFile): Observable<EntityResponseType> {
    return this.http.put<ICspaceFile>(this.resourceUrl, cspaceFile, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICspaceFile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICspaceFile[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
