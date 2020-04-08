import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';
import { MsgReceiverGroupMpService } from './msg-receiver-group-mp.service';
import { MsgReceiverGroupMpDeleteDialogComponent } from './msg-receiver-group-mp-delete-dialog.component';

@Component({
  selector: 'jhi-msg-receiver-group-mp',
  templateUrl: './msg-receiver-group-mp.component.html'
})
export class MsgReceiverGroupMpComponent implements OnInit, OnDestroy {
  msgReceiverGroups?: IMsgReceiverGroupMp[];
  eventSubscriber?: Subscription;

  constructor(
    protected msgReceiverGroupService: MsgReceiverGroupMpService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.msgReceiverGroupService.query().subscribe((res: HttpResponse<IMsgReceiverGroupMp[]>) => (this.msgReceiverGroups = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMsgReceiverGroups();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMsgReceiverGroupMp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMsgReceiverGroups(): void {
    this.eventSubscriber = this.eventManager.subscribe('msgReceiverGroupListModification', () => this.loadAll());
  }

  delete(msgReceiverGroup: IMsgReceiverGroupMp): void {
    const modalRef = this.modalService.open(MsgReceiverGroupMpDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.msgReceiverGroup = msgReceiverGroup;
  }
}
