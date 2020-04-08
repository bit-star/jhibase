import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFmpSubCompanyMp, FmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { FmpSubCompanyMpService } from './fmp-sub-company-mp.service';
import { FmpSubCompanyMpComponent } from './fmp-sub-company-mp.component';
import { FmpSubCompanyMpDetailComponent } from './fmp-sub-company-mp-detail.component';
import { FmpSubCompanyMpUpdateComponent } from './fmp-sub-company-mp-update.component';

@Injectable({ providedIn: 'root' })
export class FmpSubCompanyMpResolve implements Resolve<IFmpSubCompanyMp> {
  constructor(private service: FmpSubCompanyMpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFmpSubCompanyMp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fmpSubCompany: HttpResponse<FmpSubCompanyMp>) => {
          if (fmpSubCompany.body) {
            return of(fmpSubCompany.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FmpSubCompanyMp());
  }
}

export const fmpSubCompanyRoute: Routes = [
  {
    path: '',
    component: FmpSubCompanyMpComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.fmpSubCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FmpSubCompanyMpDetailComponent,
    resolve: {
      fmpSubCompany: FmpSubCompanyMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.fmpSubCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FmpSubCompanyMpUpdateComponent,
    resolve: {
      fmpSubCompany: FmpSubCompanyMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.fmpSubCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FmpSubCompanyMpUpdateComponent,
    resolve: {
      fmpSubCompany: FmpSubCompanyMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.fmpSubCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
