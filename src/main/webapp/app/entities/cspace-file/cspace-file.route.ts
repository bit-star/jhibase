import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CspaceFile } from 'app/shared/model/cspace-file.model';
import { CspaceFileService } from './cspace-file.service';
import { CspaceFileComponent } from './cspace-file.component';
import { CspaceFileDetailComponent } from './cspace-file-detail.component';
import { CspaceFileUpdateComponent } from './cspace-file-update.component';
import { CspaceFileDeletePopupComponent } from './cspace-file-delete-dialog.component';
import { ICspaceFile } from 'app/shared/model/cspace-file.model';

@Injectable({ providedIn: 'root' })
export class CspaceFileResolve implements Resolve<ICspaceFile> {
  constructor(private service: CspaceFileService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICspaceFile> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<CspaceFile>) => response.ok),
        map((cspaceFile: HttpResponse<CspaceFile>) => cspaceFile.body)
      );
    }
    return of(new CspaceFile());
  }
}

export const cspaceFileRoute: Routes = [
  {
    path: '',
    component: CspaceFileComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.cspaceFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CspaceFileDetailComponent,
    resolve: {
      cspaceFile: CspaceFileResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.cspaceFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CspaceFileUpdateComponent,
    resolve: {
      cspaceFile: CspaceFileResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.cspaceFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CspaceFileUpdateComponent,
    resolve: {
      cspaceFile: CspaceFileResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.cspaceFile.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const cspaceFilePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: CspaceFileDeletePopupComponent,
    resolve: {
      cspaceFile: CspaceFileResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.cspaceFile.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
