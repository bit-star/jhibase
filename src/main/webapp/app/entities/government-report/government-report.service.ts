import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGovernmentReport } from 'app/shared/model/government-report.model';

type EntityResponseType = HttpResponse<IGovernmentReport>;
type EntityArrayResponseType = HttpResponse<IGovernmentReport[]>;

@Injectable({ providedIn: 'root' })
export class GovernmentReportService {
  public resourceUrl = SERVER_API_URL + 'api/government-reports';

  constructor(protected http: HttpClient) {}

  create(governmentReport: IGovernmentReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(governmentReport);
    return this.http
      .post<IGovernmentReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(governmentReport: IGovernmentReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(governmentReport);
    return this.http
      .put<IGovernmentReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGovernmentReport>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGovernmentReport[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(governmentReport: IGovernmentReport): IGovernmentReport {
    const copy: IGovernmentReport = Object.assign({}, governmentReport, {
      reportDate: governmentReport.reportDate != null && governmentReport.reportDate.isValid() ? governmentReport.reportDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.reportDate = res.body.reportDate != null ? moment(res.body.reportDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((governmentReport: IGovernmentReport) => {
        governmentReport.reportDate = governmentReport.reportDate != null ? moment(governmentReport.reportDate) : null;
      });
    }
    return res;
  }
}
