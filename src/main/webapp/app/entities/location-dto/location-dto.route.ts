import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILocationDTO, LocationDTO } from 'app/shared/model/location-dto.model';
import { LocationDTOService } from './location-dto.service';
import { LocationDTOComponent } from './location-dto.component';
import { LocationDTODetailComponent } from './location-dto-detail.component';
import { LocationDTOUpdateComponent } from './location-dto-update.component';

@Injectable({ providedIn: 'root' })
export class LocationDTOResolve implements Resolve<ILocationDTO> {
  constructor(private service: LocationDTOService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILocationDTO> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((locationDTO: HttpResponse<LocationDTO>) => {
          if (locationDTO.body) {
            return of(locationDTO.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LocationDTO());
  }
}

export const locationDTORoute: Routes = [
  {
    path: '',
    component: LocationDTOComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationDTO.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: LocationDTODetailComponent,
    resolve: {
      locationDTO: LocationDTOResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationDTO.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: LocationDTOUpdateComponent,
    resolve: {
      locationDTO: LocationDTOResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationDTO.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: LocationDTOUpdateComponent,
    resolve: {
      locationDTO: LocationDTOResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.locationDTO.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
