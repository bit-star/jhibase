import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { FmpSubCompanyMpService } from './fmp-sub-company-mp.service';

@Component({
  templateUrl: './fmp-sub-company-mp-delete-dialog.component.html'
})
export class FmpSubCompanyMpDeleteDialogComponent {
  fmpSubCompany?: IFmpSubCompanyMp;

  constructor(
    protected fmpSubCompanyService: FmpSubCompanyMpService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fmpSubCompanyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fmpSubCompanyListModification');
      this.activeModal.close();
    });
  }
}
