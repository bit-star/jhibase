import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserProduct } from 'app/shared/model/user-product.model';
import { UserProductService } from './user-product.service';
import { UserProductComponent } from './user-product.component';
import { UserProductDetailComponent } from './user-product-detail.component';
import { UserProductUpdateComponent } from './user-product-update.component';
import { UserProductDeletePopupComponent } from './user-product-delete-dialog.component';
import { IUserProduct } from 'app/shared/model/user-product.model';

@Injectable({ providedIn: 'root' })
export class UserProductResolve implements Resolve<IUserProduct> {
  constructor(private service: UserProductService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IUserProduct> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<UserProduct>) => response.ok),
        map((userProduct: HttpResponse<UserProduct>) => userProduct.body)
      );
    }
    return of(new UserProduct());
  }
}

export const userProductRoute: Routes = [
  {
    path: '',
    component: UserProductComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.userProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UserProductDetailComponent,
    resolve: {
      userProduct: UserProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.userProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UserProductUpdateComponent,
    resolve: {
      userProduct: UserProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.userProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UserProductUpdateComponent,
    resolve: {
      userProduct: UserProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.userProduct.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const userProductPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: UserProductDeletePopupComponent,
    resolve: {
      userProduct: UserProductResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.userProduct.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
