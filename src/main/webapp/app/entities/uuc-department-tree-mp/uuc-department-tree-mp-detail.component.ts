import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';

@Component({
  selector: 'jhi-uuc-department-tree-mp-detail',
  templateUrl: './uuc-department-tree-mp-detail.component.html'
})
export class UucDepartmentTreeMpDetailComponent implements OnInit {
  uucDepartmentTree: IUucDepartmentTreeMp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ uucDepartmentTree }) => (this.uucDepartmentTree = uucDepartmentTree));
  }

  previousState(): void {
    window.history.back();
  }
}
