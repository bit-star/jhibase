import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';
import { MsgReceiverGroupMpService } from './msg-receiver-group-mp.service';

@Component({
  templateUrl: './msg-receiver-group-mp-delete-dialog.component.html'
})
export class MsgReceiverGroupMpDeleteDialogComponent {
  msgReceiverGroup?: IMsgReceiverGroupMp;

  constructor(
    protected msgReceiverGroupService: MsgReceiverGroupMpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.msgReceiverGroupService.delete(id).subscribe(() => {
      this.eventManager.broadcast('msgReceiverGroupListModification');
      this.activeModal.close();
    });
  }
}
