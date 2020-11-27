import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMicroAppGroupMp } from 'app/shared/model/micro-app-group-mp.model';
import { MicroAppGroupMpService } from './micro-app-group-mp.service';
import { MicroAppGroupMpDeleteDialogComponent } from './micro-app-group-mp-delete-dialog.component';

@Component({
  selector: 'jhi-micro-app-group-mp',
  templateUrl: './micro-app-group-mp.component.html',
})
export class MicroAppGroupMpComponent implements OnInit, OnDestroy {
  microAppGroups?: IMicroAppGroupMp[];
  eventSubscriber?: Subscription;

  constructor(
    protected microAppGroupService: MicroAppGroupMpService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.microAppGroupService.query().subscribe((res: HttpResponse<IMicroAppGroupMp[]>) => (this.microAppGroups = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMicroAppGroups();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMicroAppGroupMp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMicroAppGroups(): void {
    this.eventSubscriber = this.eventManager.subscribe('microAppGroupListModification', () => this.loadAll());
  }

  delete(microAppGroup: IMicroAppGroupMp): void {
    const modalRef = this.modalService.open(MicroAppGroupMpDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.microAppGroup = microAppGroup;
  }
}
