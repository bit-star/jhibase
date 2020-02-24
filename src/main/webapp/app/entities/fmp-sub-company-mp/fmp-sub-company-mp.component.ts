import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { FmpSubCompanyMpService } from './fmp-sub-company-mp.service';
import { FmpSubCompanyMpDeleteDialogComponent } from './fmp-sub-company-mp-delete-dialog.component';

@Component({
  selector: 'jhi-fmp-sub-company-mp',
  templateUrl: './fmp-sub-company-mp.component.html'
})
export class FmpSubCompanyMpComponent implements OnInit, OnDestroy {
  fmpSubCompanies?: IFmpSubCompanyMp[];
  eventSubscriber?: Subscription;

  constructor(
    protected fmpSubCompanyService: FmpSubCompanyMpService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.fmpSubCompanyService.query().subscribe((res: HttpResponse<IFmpSubCompanyMp[]>) => (this.fmpSubCompanies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFmpSubCompanies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFmpSubCompanyMp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFmpSubCompanies(): void {
    this.eventSubscriber = this.eventManager.subscribe('fmpSubCompanyListModification', () => this.loadAll());
  }

  delete(fmpSubCompany: IFmpSubCompanyMp): void {
    const modalRef = this.modalService.open(FmpSubCompanyMpDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fmpSubCompany = fmpSubCompany;
  }
}
