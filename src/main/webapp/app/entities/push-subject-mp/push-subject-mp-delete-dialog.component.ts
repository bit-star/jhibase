import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPushSubjectMp } from 'app/shared/model/push-subject-mp.model';
import { PushSubjectMpService } from './push-subject-mp.service';

@Component({
  templateUrl: './push-subject-mp-delete-dialog.component.html'
})
export class PushSubjectMpDeleteDialogComponent {
  pushSubject?: IPushSubjectMp;

  constructor(
    protected pushSubjectService: PushSubjectMpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.pushSubjectService.delete(id).subscribe(() => {
      this.eventManager.broadcast('pushSubjectListModification');
      this.activeModal.close();
    });
  }
}
