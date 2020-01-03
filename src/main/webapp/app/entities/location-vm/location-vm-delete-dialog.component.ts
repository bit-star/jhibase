import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILocationVM } from 'app/shared/model/location-vm.model';
import { LocationVMService } from './location-vm.service';

@Component({
  templateUrl: './location-vm-delete-dialog.component.html'
})
export class LocationVMDeleteDialogComponent {
  locationVM?: ILocationVM;

  constructor(
    protected locationVMService: LocationVMService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.locationVMService.delete(id).subscribe(() => {
      this.eventManager.broadcast('locationVMListModification');
      this.activeModal.close();
    });
  }
}
