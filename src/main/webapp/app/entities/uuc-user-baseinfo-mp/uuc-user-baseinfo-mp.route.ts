import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUucUserBaseinfoMp, UucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';
import { UucUserBaseinfoMpService } from './uuc-user-baseinfo-mp.service';
import { UucUserBaseinfoMpComponent } from './uuc-user-baseinfo-mp.component';
import { UucUserBaseinfoMpDetailComponent } from './uuc-user-baseinfo-mp-detail.component';
import { UucUserBaseinfoMpUpdateComponent } from './uuc-user-baseinfo-mp-update.component';

@Injectable({ providedIn: 'root' })
export class UucUserBaseinfoMpResolve implements Resolve<IUucUserBaseinfoMp> {
  constructor(private service: UucUserBaseinfoMpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUucUserBaseinfoMp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((uucUserBaseinfo: HttpResponse<UucUserBaseinfoMp>) => {
          if (uucUserBaseinfo.body) {
            return of(uucUserBaseinfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UucUserBaseinfoMp());
  }
}

export const uucUserBaseinfoRoute: Routes = [
  {
    path: '',
    component: UucUserBaseinfoMpComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.uucUserBaseinfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UucUserBaseinfoMpDetailComponent,
    resolve: {
      uucUserBaseinfo: UucUserBaseinfoMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.uucUserBaseinfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UucUserBaseinfoMpUpdateComponent,
    resolve: {
      uucUserBaseinfo: UucUserBaseinfoMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.uucUserBaseinfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UucUserBaseinfoMpUpdateComponent,
    resolve: {
      uucUserBaseinfo: UucUserBaseinfoMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.uucUserBaseinfo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
