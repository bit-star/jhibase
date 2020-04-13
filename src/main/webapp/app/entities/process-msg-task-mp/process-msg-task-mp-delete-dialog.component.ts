import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';
import { ProcessMsgTaskMpService } from './process-msg-task-mp.service';

@Component({
  templateUrl: './process-msg-task-mp-delete-dialog.component.html'
})
export class ProcessMsgTaskMpDeleteDialogComponent {
  processMsgTask?: IProcessMsgTaskMp;

  constructor(
    protected processMsgTaskService: ProcessMsgTaskMpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.processMsgTaskService.delete(id).subscribe(() => {
      this.eventManager.broadcast('processMsgTaskListModification');
      this.activeModal.close();
    });
  }
}
