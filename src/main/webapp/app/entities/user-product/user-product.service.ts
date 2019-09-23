import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUserProduct } from 'app/shared/model/user-product.model';

type EntityResponseType = HttpResponse<IUserProduct>;
type EntityArrayResponseType = HttpResponse<IUserProduct[]>;

@Injectable({ providedIn: 'root' })
export class UserProductService {
  public resourceUrl = SERVER_API_URL + 'api/user-products';

  constructor(protected http: HttpClient) {}

  create(userProduct: IUserProduct): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userProduct);
    return this.http
      .post<IUserProduct>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(userProduct: IUserProduct): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(userProduct);
    return this.http
      .put<IUserProduct>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUserProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUserProduct[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(userProduct: IUserProduct): IUserProduct {
    const copy: IUserProduct = Object.assign({}, userProduct, {
      createdDate: userProduct.createdDate != null && userProduct.createdDate.isValid() ? userProduct.createdDate.toJSON() : null,
      updateDate: userProduct.updateDate != null && userProduct.updateDate.isValid() ? userProduct.updateDate.toJSON() : null
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
      res.body.forEach((userProduct: IUserProduct) => {
        userProduct.createdDate = userProduct.createdDate != null ? moment(userProduct.createdDate) : null;
        userProduct.updateDate = userProduct.updateDate != null ? moment(userProduct.updateDate) : null;
      });
    }
    return res;
  }
}
