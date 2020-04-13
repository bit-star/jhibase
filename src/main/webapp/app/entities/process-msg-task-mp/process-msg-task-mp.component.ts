import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';
import { ProcessMsgTaskMpService } from './process-msg-task-mp.service';
import { ProcessMsgTaskMpDeleteDialogComponent } from './process-msg-task-mp-delete-dialog.component';

@Component({
  selector: 'jhi-process-msg-task-mp',
  templateUrl: './process-msg-task-mp.component.html'
})
export class ProcessMsgTaskMpComponent implements OnInit, OnDestroy {
  processMsgTasks?: IProcessMsgTaskMp[];
  eventSubscriber?: Subscription;

  constructor(
    protected processMsgTaskService: ProcessMsgTaskMpService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.processMsgTaskService.query().subscribe((res: HttpResponse<IProcessMsgTaskMp[]>) => (this.processMsgTasks = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProcessMsgTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProcessMsgTaskMp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProcessMsgTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('processMsgTaskListModification', () => this.loadAll());
  }

  delete(processMsgTask: IProcessMsgTaskMp): void {
    const modalRef = this.modalService.open(ProcessMsgTaskMpDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.processMsgTask = processMsgTask;
  }
}
