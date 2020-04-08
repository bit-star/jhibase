import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMsgReceiverGroupMp, MsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';
import { MsgReceiverGroupMpService } from './msg-receiver-group-mp.service';
import { MsgReceiverGroupMpComponent } from './msg-receiver-group-mp.component';
import { MsgReceiverGroupMpDetailComponent } from './msg-receiver-group-mp-detail.component';
import { MsgReceiverGroupMpUpdateComponent } from './msg-receiver-group-mp-update.component';

@Injectable({ providedIn: 'root' })
export class MsgReceiverGroupMpResolve implements Resolve<IMsgReceiverGroupMp> {
  constructor(private service: MsgReceiverGroupMpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMsgReceiverGroupMp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((msgReceiverGroup: HttpResponse<MsgReceiverGroupMp>) => {
          if (msgReceiverGroup.body) {
            return of(msgReceiverGroup.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MsgReceiverGroupMp());
  }
}

export const msgReceiverGroupRoute: Routes = [
  {
    path: '',
    component: MsgReceiverGroupMpComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.msgReceiverGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MsgReceiverGroupMpDetailComponent,
    resolve: {
      msgReceiverGroup: MsgReceiverGroupMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.msgReceiverGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MsgReceiverGroupMpUpdateComponent,
    resolve: {
      msgReceiverGroup: MsgReceiverGroupMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.msgReceiverGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MsgReceiverGroupMpUpdateComponent,
    resolve: {
      msgReceiverGroup: MsgReceiverGroupMpResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhibaseApp.msgReceiverGroup.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
