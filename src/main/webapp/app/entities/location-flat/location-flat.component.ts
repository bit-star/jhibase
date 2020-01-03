import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILocationFlat } from 'app/shared/model/location-flat.model';
import { LocationFlatService } from './location-flat.service';
import { LocationFlatDeleteDialogComponent } from './location-flat-delete-dialog.component';

@Component({
  selector: 'jhi-location-flat',
  templateUrl: './location-flat.component.html'
})
export class LocationFlatComponent implements OnInit, OnDestroy {
  locationFlats?: ILocationFlat[];
  eventSubscriber?: Subscription;

  constructor(
    protected locationFlatService: LocationFlatService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.locationFlatService.query().subscribe((res: HttpResponse<ILocationFlat[]>) => {
      this.locationFlats = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLocationFlats();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILocationFlat): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLocationFlats(): void {
    this.eventSubscriber = this.eventManager.subscribe('locationFlatListModification', () => this.loadAll());
  }

  delete(locationFlat: ILocationFlat): void {
    const modalRef = this.modalService.open(LocationFlatDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.locationFlat = locationFlat;
  }
}
