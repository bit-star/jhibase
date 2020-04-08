import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';
import { UucDepartmentTreeMpService } from './uuc-department-tree-mp.service';
import { UucDepartmentTreeMpDeleteDialogComponent } from './uuc-department-tree-mp-delete-dialog.component';

@Component({
  selector: 'jhi-uuc-department-tree-mp',
  templateUrl: './uuc-department-tree-mp.component.html'
})
export class UucDepartmentTreeMpComponent implements OnInit, OnDestroy {
  uucDepartmentTrees?: IUucDepartmentTreeMp[];
  eventSubscriber?: Subscription;

  constructor(
    protected uucDepartmentTreeService: UucDepartmentTreeMpService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.uucDepartmentTreeService
      .query()
      .subscribe((res: HttpResponse<IUucDepartmentTreeMp[]>) => (this.uucDepartmentTrees = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUucDepartmentTrees();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUucDepartmentTreeMp): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUucDepartmentTrees(): void {
    this.eventSubscriber = this.eventManager.subscribe('uucDepartmentTreeListModification', () => this.loadAll());
  }

  delete(uucDepartmentTree: IUucDepartmentTreeMp): void {
    const modalRef = this.modalService.open(UucDepartmentTreeMpDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.uucDepartmentTree = uucDepartmentTree;
  }
}
