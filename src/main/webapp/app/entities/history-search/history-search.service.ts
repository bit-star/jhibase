import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHistorySearch } from 'app/shared/model/history-search.model';

type EntityResponseType = HttpResponse<IHistorySearch>;
type EntityArrayResponseType = HttpResponse<IHistorySearch[]>;

@Injectable({ providedIn: 'root' })
export class HistorySearchService {
  public resourceUrl = SERVER_API_URL + 'api/history-searches';

  constructor(protected http: HttpClient) {}

  create(historySearch: IHistorySearch): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historySearch);
    return this.http
      .post<IHistorySearch>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(historySearch: IHistorySearch): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(historySearch);
    return this.http
      .put<IHistorySearch>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHistorySearch>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHistorySearch[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(historySearch: IHistorySearch): IHistorySearch {
    const copy: IHistorySearch = Object.assign({}, historySearch, {
      createdDate: historySearch.createdDate != null && historySearch.createdDate.isValid() ? historySearch.createdDate.toJSON() : null,
      updateDate: historySearch.updateDate != null && historySearch.updateDate.isValid() ? historySearch.updateDate.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate != null ? moment(res.body.createdDate) : null;
      res.body.updateDate = res.body.updateDate != null ? moment(res.body.updateDate) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((historySearch: IHistorySearch) => {
        historySearch.createdDate = historySearch.createdDate != null ? moment(historySearch.createdDate) : null;
        historySearch.updateDate = historySearch.updateDate != null ? moment(historySearch.updateDate) : null;
      });
    }
    return res;
  }
}
