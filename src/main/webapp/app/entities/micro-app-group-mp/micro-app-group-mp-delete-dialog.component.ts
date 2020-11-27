import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMicroAppGroupMp } from 'app/shared/model/micro-app-group-mp.model';
import { MicroAppGroupMpService } from './micro-app-group-mp.service';

@Component({
  templateUrl: './micro-app-group-mp-delete-dialog.component.html',
})
export class MicroAppGroupMpDeleteDialogComponent {
  microAppGroup?: IMicroAppGroupMp;

  constructor(
    protected microAppGroupService: MicroAppGroupMpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.microAppGroupService.delete(id).subscribe(() => {
      this.eventManager.broadcast('microAppGroupListModification');
      this.activeModal.close();
    });
  }
}
