import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPushSubjectMp } from 'app/shared/model/push-subject-mp.model';
import { PushSubjectMpService } from './push-subject-mp.service';
import { PushSubjectMpDeleteDialogComponent } from './push-subject-mp-delete-dialog.component';

@Component({
  selector: 'jhi-push-subject-mp',
  templateUrl: './push-subject-mp.component.html'
})
export class PushSubjectMpComponent implements OnInit, OnDestroy {
  pushSubjects?: IPushSubjectMp[];
  eventSubscriber?: Subscription;

  constructor(
    protected pushSubjectService: PushSubjectMpService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.pushSubjectService.query().subscribe((res: HttpResponse<IPushSubjectMp[]>) => (this.pushSubjects = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPushSubjects();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPushSubjectMp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPushSubjects(): void {
    this.eventSubscriber = this.eventManager.subscribe('pushSubjectListModification', () => this.loadAll());
  }

  delete(pushSubject: IPushSubjectMp): void {
    const modalRef = this.modalService.open(PushSubjectMpDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.pushSubject = pushSubject;
  }
}
