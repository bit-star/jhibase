import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUucDepartmentTreeMp, UucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';
import { UucDepartmentTreeMpService } from './uuc-department-tree-mp.service';
import { UucDepartmentTreeMpComponent } from './uuc-department-tree-mp.component';
import { UucDepartmentTreeMpDetailComponent } from './uuc-department-tree-mp-detail.component';
import { UucDepartmentTreeMpUpdateComponent } from './uuc-department-tree-mp-update.component';

@Injectable({ providedIn: 'root' })
export class UucDepartmentTreeMpResolve implements Resolve<IUucDepartmentTreeMp> {
  constructor(private service: UucDepartmentTreeMpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUucDepartmentTreeMp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((uucDepartmentTree: HttpResponse<UucDepartmentTreeMp>) => {
          if (uucDepartmentTree.body) {
            return of(uucDepartmentTree.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UucDepartmentTreeMp());
  }
}

export const uucDepartmentTreeRoute: Routes = [
  {
    path: '',
    component: UucDepartmentTreeMpComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.uucDepartmentTree.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: UucDepartmentTreeMpDetailComponent,
    resolve: {
      uucDepartmentTree: UucDepartmentTreeMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.uucDepartmentTree.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: UucDepartmentTreeMpUpdateComponent,
    resolve: {
      uucDepartmentTree: UucDepartmentTreeMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.uucDepartmentTree.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: UucDepartmentTreeMpUpdateComponent,
    resolve: {
      uucDepartmentTree: UucDepartmentTreeMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.uucDepartmentTree.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
