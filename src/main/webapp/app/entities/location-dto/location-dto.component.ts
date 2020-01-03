import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILocationDTO } from 'app/shared/model/location-dto.model';
import { LocationDTOService } from './location-dto.service';
import { LocationDTODeleteDialogComponent } from './location-dto-delete-dialog.component';

@Component({
  selector: 'jhi-location-dto',
  templateUrl: './location-dto.component.html'
})
export class LocationDTOComponent implements OnInit, OnDestroy {
  locationDTOS?: ILocationDTO[];
  eventSubscriber?: Subscription;

  constructor(
    protected locationDTOService: LocationDTOService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.locationDTOService.query().subscribe((res: HttpResponse<ILocationDTO[]>) => {
      this.locationDTOS = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLocationDTOS();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILocationDTO): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLocationDTOS(): void {
    this.eventSubscriber = this.eventManager.subscribe('locationDTOListModification', () => this.loadAll());
  }

  delete(locationDTO: ILocationDTO): void {
    const modalRef = this.modalService.open(LocationDTODeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.locationDTO = locationDTO;
  }
}
