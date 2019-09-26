import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GovernmentReport } from 'app/shared/model/government-report.model';
import { GovernmentReportService } from './government-report.service';
import { GovernmentReportComponent } from './government-report.component';
import { GovernmentReportDetailComponent } from './government-report-detail.component';
import { GovernmentReportUpdateComponent } from './government-report-update.component';
import { GovernmentReportDeletePopupComponent } from './government-report-delete-dialog.component';
import { IGovernmentReport } from 'app/shared/model/government-report.model';

@Injectable({ providedIn: 'root' })
export class GovernmentReportResolve implements Resolve<IGovernmentReport> {
  constructor(private service: GovernmentReportService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGovernmentReport> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<GovernmentReport>) => response.ok),
        map((governmentReport: HttpResponse<GovernmentReport>) => governmentReport.body)
      );
    }
    return of(new GovernmentReport());
  }
}

export const governmentReportRoute: Routes = [
  {
    path: '',
    component: GovernmentReportComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.governmentReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GovernmentReportDetailComponent,
    resolve: {
      governmentReport: GovernmentReportResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.governmentReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GovernmentReportUpdateComponent,
    resolve: {
      governmentReport: GovernmentReportResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.governmentReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GovernmentReportUpdateComponent,
    resolve: {
      governmentReport: GovernmentReportResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.governmentReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const governmentReportPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GovernmentReportDeletePopupComponent,
    resolve: {
      governmentReport: GovernmentReportResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.governmentReport.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
