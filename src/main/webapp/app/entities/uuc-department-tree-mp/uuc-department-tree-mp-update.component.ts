import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUucDepartmentTreeMp, UucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';
import { UucDepartmentTreeMpService } from './uuc-department-tree-mp.service';

@Component({
  selector: 'jhi-uuc-department-tree-mp-update',
  templateUrl: './uuc-department-tree-mp-update.component.html'
})
export class UucDepartmentTreeMpUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(
    protected uucDepartmentTreeService: UucDepartmentTreeMpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ uucDepartmentTree }) => {
      this.updateForm(uucDepartmentTree);
    });
  }

  updateForm(uucDepartmentTree: IUucDepartmentTreeMp): void {
    this.editForm.patchValue({
      id: uucDepartmentTree.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const uucDepartmentTree = this.createFromForm();
    if (uucDepartmentTree.id !== undefined) {
      this.subscribeToSaveResponse(this.uucDepartmentTreeService.update(uucDepartmentTree));
    } else {
      this.subscribeToSaveResponse(this.uucDepartmentTreeService.create(uucDepartmentTree));
    }
  }

  private createFromForm(): IUucDepartmentTreeMp {
    return {
      ...new UucDepartmentTreeMp(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUucDepartmentTreeMp>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
