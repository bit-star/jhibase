import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';

type EntityResponseType = HttpResponse<IProcessMsgTaskMp>;
type EntityArrayResponseType = HttpResponse<IProcessMsgTaskMp[]>;

@Injectable({ providedIn: 'root' })
export class ProcessMsgTaskMpService {
  public resourceUrl = SERVER_API_URL + 'api/process-msg-tasks';

  constructor(protected http: HttpClient) {}

  create(processMsgTask: IProcessMsgTaskMp): Observable<EntityResponseType> {
    return this.http.post<IProcessMsgTaskMp>(this.resourceUrl, processMsgTask, { observe: 'response' });
  }

  update(processMsgTask: IProcessMsgTaskMp): Observable<EntityResponseType> {
    return this.http.put<IProcessMsgTaskMp>(this.resourceUrl, processMsgTask, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProcessMsgTaskMp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProcessMsgTaskMp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
