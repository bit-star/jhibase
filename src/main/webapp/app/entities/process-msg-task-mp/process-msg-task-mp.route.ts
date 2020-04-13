import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProcessMsgTaskMp, ProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';
import { ProcessMsgTaskMpService } from './process-msg-task-mp.service';
import { ProcessMsgTaskMpComponent } from './process-msg-task-mp.component';
import { ProcessMsgTaskMpDetailComponent } from './process-msg-task-mp-detail.component';
import { ProcessMsgTaskMpUpdateComponent } from './process-msg-task-mp-update.component';

@Injectable({ providedIn: 'root' })
export class ProcessMsgTaskMpResolve implements Resolve<IProcessMsgTaskMp> {
  constructor(private service: ProcessMsgTaskMpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProcessMsgTaskMp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((processMsgTask: HttpResponse<ProcessMsgTaskMp>) => {
          if (processMsgTask.body) {
            return of(processMsgTask.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProcessMsgTaskMp());
  }
}

export const processMsgTaskRoute: Routes = [
  {
    path: '',
    component: ProcessMsgTaskMpComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.processMsgTask.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ProcessMsgTaskMpDetailComponent,
    resolve: {
      processMsgTask: ProcessMsgTaskMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.processMsgTask.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ProcessMsgTaskMpUpdateComponent,
    resolve: {
      processMsgTask: ProcessMsgTaskMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.processMsgTask.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ProcessMsgTaskMpUpdateComponent,
    resolve: {
      processMsgTask: ProcessMsgTaskMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.processMsgTask.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
