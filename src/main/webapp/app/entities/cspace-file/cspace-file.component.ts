import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICspaceFile } from 'app/shared/model/cspace-file.model';
import { AccountService } from 'app/core/auth/account.service';
import { CspaceFileService } from './cspace-file.service';

@Component({
  selector: 'jhi-cspace-file',
  templateUrl: './cspace-file.component.html'
})
export class CspaceFileComponent implements OnInit, OnDestroy {
  cspaceFiles: ICspaceFile[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected cspaceFileService: CspaceFileService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.cspaceFileService
      .query()
      .pipe(
        filter((res: HttpResponse<ICspaceFile[]>) => res.ok),
        map((res: HttpResponse<ICspaceFile[]>) => res.body)
      )
      .subscribe(
        (res: ICspaceFile[]) => {
          this.cspaceFiles = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInCspaceFiles();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: ICspaceFile) {
    return item.id;
  }

  registerChangeInCspaceFiles() {
    this.eventSubscriber = this.eventManager.subscribe('cspaceFileListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
