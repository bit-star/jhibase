import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPushSubjectMp, PushSubjectMp } from 'app/shared/model/push-subject-mp.model';
import { PushSubjectMpService } from './push-subject-mp.service';
import { PushSubjectMpComponent } from './push-subject-mp.component';
import { PushSubjectMpDetailComponent } from './push-subject-mp-detail.component';
import { PushSubjectMpUpdateComponent } from './push-subject-mp-update.component';

@Injectable({ providedIn: 'root' })
export class PushSubjectMpResolve implements Resolve<IPushSubjectMp> {
  constructor(private service: PushSubjectMpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPushSubjectMp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((pushSubject: HttpResponse<PushSubjectMp>) => {
          if (pushSubject.body) {
            return of(pushSubject.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PushSubjectMp());
  }
}

export const pushSubjectRoute: Routes = [
  {
    path: '',
    component: PushSubjectMpComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.pushSubject.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PushSubjectMpDetailComponent,
    resolve: {
      pushSubject: PushSubjectMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.pushSubject.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PushSubjectMpUpdateComponent,
    resolve: {
      pushSubject: PushSubjectMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.pushSubject.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PushSubjectMpUpdateComponent,
    resolve: {
      pushSubject: PushSubjectMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.pushSubject.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
