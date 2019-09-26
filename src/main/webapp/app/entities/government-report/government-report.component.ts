import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IGovernmentReport } from 'app/shared/model/government-report.model';
import { AccountService } from 'app/core/auth/account.service';
import { GovernmentReportService } from './government-report.service';

@Component({
  selector: 'jhi-government-report',
  templateUrl: './government-report.component.html'
})
export class GovernmentReportComponent implements OnInit, OnDestroy {
  governmentReports: IGovernmentReport[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected governmentReportService: GovernmentReportService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.governmentReportService
      .query()
      .pipe(
        filter((res: HttpResponse<IGovernmentReport[]>) => res.ok),
        map((res: HttpResponse<IGovernmentReport[]>) => res.body)
      )
      .subscribe(
        (res: IGovernmentReport[]) => {
          this.governmentReports = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInGovernmentReports();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGovernmentReport) {
    return item.id;
  }

  registerChangeInGovernmentReports() {
    this.eventSubscriber = this.eventManager.subscribe('governmentReportListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
