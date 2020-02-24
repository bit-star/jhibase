import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IServiceWindowMp, ServiceWindowMp } from 'app/shared/model/service-window-mp.model';
import { ServiceWindowMpService } from './service-window-mp.service';
import { ServiceWindowMpComponent } from './service-window-mp.component';
import { ServiceWindowMpDetailComponent } from './service-window-mp-detail.component';
import { ServiceWindowMpUpdateComponent } from './service-window-mp-update.component';

@Injectable({ providedIn: 'root' })
export class ServiceWindowMpResolve implements Resolve<IServiceWindowMp> {
  constructor(private service: ServiceWindowMpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IServiceWindowMp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((serviceWindow: HttpResponse<ServiceWindowMp>) => {
          if (serviceWindow.body) {
            return of(serviceWindow.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ServiceWindowMp());
  }
}

export const serviceWindowRoute: Routes = [
  {
    path: '',
    component: ServiceWindowMpComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.serviceWindow.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ServiceWindowMpDetailComponent,
    resolve: {
      serviceWindow: ServiceWindowMpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.serviceWindow.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ServiceWindowMpUpdateComponent,
    resolve: {
      serviceWindow: ServiceWindowMpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.serviceWindow.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ServiceWindowMpUpdateComponent,
    resolve: {
      serviceWindow: ServiceWindowMpResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.serviceWindow.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
