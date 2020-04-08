import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';
import { UucDepartmentTreeMpService } from './uuc-department-tree-mp.service';

@Component({
  templateUrl: './uuc-department-tree-mp-delete-dialog.component.html'
})
export class UucDepartmentTreeMpDeleteDialogComponent {
  uucDepartmentTree?: IUucDepartmentTreeMp;

  constructor(
    protected uucDepartmentTreeService: UucDepartmentTreeMpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.uucDepartmentTreeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('uucDepartmentTreeListModification');
      this.activeModal.close();
    });
  }
}
