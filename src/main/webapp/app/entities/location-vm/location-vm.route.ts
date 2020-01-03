import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILocationVM, LocationVM } from 'app/shared/model/location-vm.model';
import { LocationVMService } from './location-vm.service';
import { LocationVMComponent } from './location-vm.component';
import { LocationVMDetailComponent } from './location-vm-detail.component';
import { LocationVMUpdateComponent } from './location-vm-update.component';

@Injectable({ providedIn: 'root' })
export class LocationVMResolve implements Resolve<ILocationVM> {
  constructor(private service: LocationVMService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILocationVM> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((locationVM: HttpResponse<LocationVM>) => {
          if (locationVM.body) {
            return of(locationVM.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LocationVM());
  }
}

export const locationVMRoute: Routes = [
  {
    path: '',
    component: LocationVMComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationVM.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LocationVMDetailComponent,
    resolve: {
      locationVM: LocationVMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationVM.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LocationVMUpdateComponent,
    resolve: {
      locationVM: LocationVMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationVM.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LocationVMUpdateComponent,
    resolve: {
      locationVM: LocationVMResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationVM.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
