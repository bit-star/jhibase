import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILocationVM } from 'app/shared/model/location-vm.model';
import { LocationVMService } from './location-vm.service';
import { LocationVMDeleteDialogComponent } from './location-vm-delete-dialog.component';

@Component({
  selector: 'jhi-location-vm',
  templateUrl: './location-vm.component.html'
})
export class LocationVMComponent implements OnInit, OnDestroy {
  locationVMS?: ILocationVM[];
  eventSubscriber?: Subscription;

  constructor(protected locationVMService: LocationVMService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.locationVMService.query().subscribe((res: HttpResponse<ILocationVM[]>) => {
      this.locationVMS = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLocationVMS();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILocationVM): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLocationVMS(): void {
    this.eventSubscriber = this.eventManager.subscribe('locationVMListModification', () => this.loadAll());
  }

  delete(locationVM: ILocationVM): void {
    const modalRef = this.modalService.open(LocationVMDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.locationVM = locationVM;
  }
}
