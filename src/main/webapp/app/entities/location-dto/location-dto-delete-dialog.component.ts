import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILocationDTO } from 'app/shared/model/location-dto.model';
import { LocationDTOService } from './location-dto.service';

@Component({
  templateUrl: './location-dto-delete-dialog.component.html'
})
export class LocationDTODeleteDialogComponent {
  locationDTO?: ILocationDTO;

  constructor(
    protected locationDTOService: LocationDTOService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.locationDTOService.delete(id).subscribe(() => {
      this.eventManager.broadcast('locationDTOListModification');
      this.activeModal.close();
    });
  }
}
