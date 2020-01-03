import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILocationFlat, LocationFlat } from 'app/shared/model/location-flat.model';
import { LocationFlatService } from './location-flat.service';
import { LocationFlatComponent } from './location-flat.component';
import { LocationFlatDetailComponent } from './location-flat-detail.component';
import { LocationFlatUpdateComponent } from './location-flat-update.component';

@Injectable({ providedIn: 'root' })
export class LocationFlatResolve implements Resolve<ILocationFlat> {
  constructor(private service: LocationFlatService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILocationFlat> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((locationFlat: HttpResponse<LocationFlat>) => {
          if (locationFlat.body) {
            return of(locationFlat.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LocationFlat());
  }
}

export const locationFlatRoute: Routes = [
  {
    path: '',
    component: LocationFlatComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationFlat.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LocationFlatDetailComponent,
    resolve: {
      locationFlat: LocationFlatResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationFlat.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LocationFlatUpdateComponent,
    resolve: {
      locationFlat: LocationFlatResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationFlat.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LocationFlatUpdateComponent,
    resolve: {
      locationFlat: LocationFlatResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationFlat.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
