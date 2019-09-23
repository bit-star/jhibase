import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IUserProduct } from 'app/shared/model/user-product.model';
import { AccountService } from 'app/core/auth/account.service';
import { UserProductService } from './user-product.service';

@Component({
  selector: 'jhi-user-product',
  templateUrl: './user-product.component.html'
})
export class UserProductComponent implements OnInit, OnDestroy {
  userProducts: IUserProduct[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected userProductService: UserProductService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.userProductService
      .query()
      .pipe(
        filter((res: HttpResponse<IUserProduct[]>) => res.ok),
        map((res: HttpResponse<IUserProduct[]>) => res.body)
      )
      .subscribe(
        (res: IUserProduct[]) => {
          this.userProducts = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInUserProducts();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IUserProduct) {
    return item.id;
  }

  registerChangeInUserProducts() {
    this.eventSubscriber = this.eventManager.subscribe('userProductListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
