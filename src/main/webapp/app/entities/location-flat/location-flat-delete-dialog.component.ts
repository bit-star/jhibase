import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILocationFlat } from 'app/shared/model/location-flat.model';
import { LocationFlatService } from './location-flat.service';

@Component({
  templateUrl: './location-flat-delete-dialog.component.html'
})
export class LocationFlatDeleteDialogComponent {
  locationFlat?: ILocationFlat;

  constructor(
    protected locationFlatService: LocationFlatService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.locationFlatService.delete(id).subscribe(() => {
      this.eventManager.broadcast('locationFlatListModification');
      this.activeModal.close();
    });
  }
}
