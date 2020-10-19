import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMpHotspotMp } from 'app/shared/model/mp-hotspot-mp.model';
import { MpHotspotMpService } from './mp-hotspot-mp.service';
import { MpHotspotMpDeleteDialogComponent } from './mp-hotspot-mp-delete-dialog.component';

@Component({
  selector: 'jhi-mp-hotspot-mp',
  templateUrl: './mp-hotspot-mp.component.html',
})
export class MpHotspotMpComponent implements OnInit, OnDestroy {
  mpHotspots?: IMpHotspotMp[];
  eventSubscriber?: Subscription;

  constructor(protected mpHotspotService: MpHotspotMpService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.mpHotspotService.query().subscribe((res: HttpResponse<IMpHotspotMp[]>) => (this.mpHotspots = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMpHotspots();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMpHotspotMp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMpHotspots(): void {
    this.eventSubscriber = this.eventManager.subscribe('mpHotspotListModification', () => this.loadAll());
  }

  delete(mpHotspot: IMpHotspotMp): void {
    const modalRef = this.modalService.open(MpHotspotMpDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mpHotspot = mpHotspot;
  }
}
