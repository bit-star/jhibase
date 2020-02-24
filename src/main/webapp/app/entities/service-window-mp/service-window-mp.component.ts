import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IServiceWindowMp } from 'app/shared/model/service-window-mp.model';
import { ServiceWindowMpService } from './service-window-mp.service';
import { ServiceWindowMpDeleteDialogComponent } from './service-window-mp-delete-dialog.component';

@Component({
  selector: 'jhi-service-window-mp',
  templateUrl: './service-window-mp.component.html'
})
export class ServiceWindowMpComponent implements OnInit, OnDestroy {
  serviceWindows?: IServiceWindowMp[];
  eventSubscriber?: Subscription;

  constructor(
    protected serviceWindowService: ServiceWindowMpService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.serviceWindowService.query().subscribe((res: HttpResponse<IServiceWindowMp[]>) => (this.serviceWindows = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInServiceWindows();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IServiceWindowMp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInServiceWindows(): void {
    this.eventSubscriber = this.eventManager.subscribe('serviceWindowListModification', () => this.loadAll());
  }

  delete(serviceWindow: IServiceWindowMp): void {
    const modalRef = this.modalService.open(ServiceWindowMpDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.serviceWindow = serviceWindow;
  }
}
