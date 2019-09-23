import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IUserProduct, UserProduct } from 'app/shared/model/user-product.model';
import { UserProductService } from './user-product.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';

@Component({
  selector: 'jhi-user-product-update',
  templateUrl: './user-product-update.component.html'
})
export class UserProductUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    productName: [],
    productDes: [],
    imgURL: [],
    createdDate: [],
    updateDate: [],
    user: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected userProductService: UserProductService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ userProduct }) => {
      this.updateForm(userProduct);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(userProduct: IUserProduct) {
    this.editForm.patchValue({
      id: userProduct.id,
      productName: userProduct.productName,
      productDes: userProduct.productDes,
      imgURL: userProduct.imgURL,
      createdDate: userProduct.createdDate != null ? userProduct.createdDate.format(DATE_TIME_FORMAT) : null,
      updateDate: userProduct.updateDate != null ? userProduct.updateDate.format(DATE_TIME_FORMAT) : null,
      user: userProduct.user
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const userProduct = this.createFromForm();
    if (userProduct.id !== undefined) {
      this.subscribeToSaveResponse(this.userProductService.update(userProduct));
    } else {
      this.subscribeToSaveResponse(this.userProductService.create(userProduct));
    }
  }

  private createFromForm(): IUserProduct {
    return {
      ...new UserProduct(),
      id: this.editForm.get(['id']).value,
      productName: this.editForm.get(['productName']).value,
      productDes: this.editForm.get(['productDes']).value,
      imgURL: this.editForm.get(['imgURL']).value,
      createdDate:
        this.editForm.get(['createdDate']).value != null ? moment(this.editForm.get(['createdDate']).value, DATE_TIME_FORMAT) : undefined,
      updateDate:
        this.editForm.get(['updateDate']).value != null ? moment(this.editForm.get(['updateDate']).value, DATE_TIME_FORMAT) : undefined,
      user: this.editForm.get(['user']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserProduct>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }
}
