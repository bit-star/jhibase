import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';
import { UucUserBaseinfoMpService } from './uuc-user-baseinfo-mp.service';
import { UucUserBaseinfoMpDeleteDialogComponent } from './uuc-user-baseinfo-mp-delete-dialog.component';

@Component({
  selector: 'jhi-uuc-user-baseinfo-mp',
  templateUrl: './uuc-user-baseinfo-mp.component.html'
})
export class UucUserBaseinfoMpComponent implements OnInit, OnDestroy {
  uucUserBaseinfos?: IUucUserBaseinfoMp[];
  eventSubscriber?: Subscription;

  constructor(
    protected uucUserBaseinfoService: UucUserBaseinfoMpService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.uucUserBaseinfoService.query().subscribe((res: HttpResponse<IUucUserBaseinfoMp[]>) => (this.uucUserBaseinfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUucUserBaseinfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUucUserBaseinfoMp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUucUserBaseinfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('uucUserBaseinfoListModification', () => this.loadAll());
  }

  delete(uucUserBaseinfo: IUucUserBaseinfoMp): void {
    const modalRef = this.modalService.open(UucUserBaseinfoMpDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.uucUserBaseinfo = uucUserBaseinfo;
  }
}
