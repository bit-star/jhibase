import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMicroAppGroupMp, MicroAppGroupMp } from 'app/shared/model/micro-app-group-mp.model';
import { MicroAppGroupMpService } from './micro-app-group-mp.service';
import { MicroAppGroupMpComponent } from './micro-app-group-mp.component';
import { MicroAppGroupMpDetailComponent } from './micro-app-group-mp-detail.component';
import { MicroAppGroupMpUpdateComponent } from './micro-app-group-mp-update.component';

@Injectable({ providedIn: 'root' })
export class MicroAppGroupMpResolve implements Resolve<IMicroAppGroupMp> {
  constructor(private service: MicroAppGroupMpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMicroAppGroupMp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((microAppGroup: HttpResponse<MicroAppGroupMp>) => {
          if (microAppGroup.body) {
            return of(microAppGroup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MicroAppGroupMp());
  }
}

export const microAppGroupRoute: Routes = [
  {
    path: '',
    component: MicroAppGroupMpComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.microAppGroup.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MicroAppGroupMpDetailComponent,
    resolve: {
      microAppGroup: MicroAppGroupMpResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.microAppGroup.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MicroAppGroupMpUpdateComponent,
    resolve: {
      microAppGroup: MicroAppGroupMpResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.microAppGroup.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MicroAppGroupMpUpdateComponent,
    resolve: {
      microAppGroup: MicroAppGroupMpResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.microAppGroup.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
