import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';
import { UucUserBaseinfoMpService } from './uuc-user-baseinfo-mp.service';

@Component({
  templateUrl: './uuc-user-baseinfo-mp-delete-dialog.component.html'
})
export class UucUserBaseinfoMpDeleteDialogComponent {
  uucUserBaseinfo?: IUucUserBaseinfoMp;

  constructor(
    protected uucUserBaseinfoService: UucUserBaseinfoMpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.uucUserBaseinfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('uucUserBaseinfoListModification');
      this.activeModal.close();
    });
  }
}
