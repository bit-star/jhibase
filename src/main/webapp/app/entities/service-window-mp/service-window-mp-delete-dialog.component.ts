import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IServiceWindowMp } from 'app/shared/model/service-window-mp.model';
import { ServiceWindowMpService } from './service-window-mp.service';

@Component({
  templateUrl: './service-window-mp-delete-dialog.component.html'
})
export class ServiceWindowMpDeleteDialogComponent {
  serviceWindow?: IServiceWindowMp;

  constructor(
    protected serviceWindowService: ServiceWindowMpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.serviceWindowService.delete(id).subscribe(() => {
      this.eventManager.broadcast('serviceWindowListModification');
      this.activeModal.close();
    });
  }
}
