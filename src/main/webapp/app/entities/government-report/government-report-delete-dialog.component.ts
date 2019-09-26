import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGovernmentReport } from 'app/shared/model/government-report.model';
import { GovernmentReportService } from './government-report.service';

@Component({
  selector: 'jhi-government-report-delete-dialog',
  templateUrl: './government-report-delete-dialog.component.html'
})
export class GovernmentReportDeleteDialogComponent {
  governmentReport: IGovernmentReport;

  constructor(
    protected governmentReportService: GovernmentReportService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.governmentReportService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'governmentReportListModification',
        content: 'Deleted an governmentReport'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-government-report-delete-popup',
  template: ''
})
export class GovernmentReportDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ governmentReport }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GovernmentReportDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.governmentReport = governmentReport;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/government-report', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/government-report', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
