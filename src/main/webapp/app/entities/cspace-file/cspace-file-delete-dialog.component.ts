import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICspaceFile } from 'app/shared/model/cspace-file.model';
import { CspaceFileService } from './cspace-file.service';

@Component({
  selector: 'jhi-cspace-file-delete-dialog',
  templateUrl: './cspace-file-delete-dialog.component.html'
})
export class CspaceFileDeleteDialogComponent {
  cspaceFile: ICspaceFile;

  constructor(
    protected cspaceFileService: CspaceFileService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.cspaceFileService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'cspaceFileListModification',
        content: 'Deleted an cspaceFile'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-cspace-file-delete-popup',
  template: ''
})
export class CspaceFileDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ cspaceFile }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(CspaceFileDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.cspaceFile = cspaceFile;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/cspace-file', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/cspace-file', { outlets: { popup: null } }]);
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
