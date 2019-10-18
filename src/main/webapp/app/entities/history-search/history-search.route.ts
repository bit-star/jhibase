import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HistorySearch } from 'app/shared/model/history-search.model';
import { HistorySearchService } from './history-search.service';
import { HistorySearchComponent } from './history-search.component';
import { HistorySearchDetailComponent } from './history-search-detail.component';
import { HistorySearchUpdateComponent } from './history-search-update.component';
import { HistorySearchDeletePopupComponent } from './history-search-delete-dialog.component';
import { IHistorySearch } from 'app/shared/model/history-search.model';

@Injectable({ providedIn: 'root' })
export class HistorySearchResolve implements Resolve<IHistorySearch> {
  constructor(private service: HistorySearchService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHistorySearch> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<HistorySearch>) => response.ok),
        map((historySearch: HttpResponse<HistorySearch>) => historySearch.body)
      );
    }
    return of(new HistorySearch());
  }
}

export const historySearchRoute: Routes = [
  {
    path: '',
    component: HistorySearchComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'jhibaseApp.historySearch.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: HistorySearchDetailComponent,
    resolve: {
      historySearch: HistorySearchResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.historySearch.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: HistorySearchUpdateComponent,
    resolve: {
      historySearch: HistorySearchResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.historySearch.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: HistorySearchUpdateComponent,
    resolve: {
      historySearch: HistorySearchResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.historySearch.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const historySearchPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: HistorySearchDeletePopupComponent,
    resolve: {
      historySearch: HistorySearchResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'jhibaseApp.historySearch.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
