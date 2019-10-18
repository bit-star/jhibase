import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHistorySearch } from 'app/shared/model/history-search.model';
import { HistorySearchService } from './history-search.service';

@Component({
  selector: 'jhi-history-search-delete-dialog',
  templateUrl: './history-search-delete-dialog.component.html'
})
export class HistorySearchDeleteDialogComponent {
  historySearch: IHistorySearch;

  constructor(
    protected historySearchService: HistorySearchService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.historySearchService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'historySearchListModification',
        content: 'Deleted an historySearch'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-history-search-delete-popup',
  template: ''
})
export class HistorySearchDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ historySearch }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(HistorySearchDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.historySearch = historySearch;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/history-search', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/history-search', { outlets: { popup: null } }]);
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
