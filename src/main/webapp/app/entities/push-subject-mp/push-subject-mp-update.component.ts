import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPushSubjectMp, PushSubjectMp } from 'app/shared/model/push-subject-mp.model';
import { PushSubjectMpService } from './push-subject-mp.service';
import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { FmpSubCompanyMpService } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp.service';

@Component({
  selector: 'jhi-push-subject-mp-update',
  templateUrl: './push-subject-mp-update.component.html'
})
export class PushSubjectMpUpdateComponent implements OnInit {
  isSaving = false;
  fmpsubcompanies: IFmpSubCompanyMp[] = [];

  editForm = this.fb.group({
    id: [],
    agentId: [],
    name: [],
    agentGroupId: [],
    titleColor: [],
    remark: [],
    fmpSubCompany: []
  });

  constructor(
    protected pushSubjectService: PushSubjectMpService,
    protected fmpSubCompanyService: FmpSubCompanyMpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pushSubject }) => {
      this.updateForm(pushSubject);

      this.fmpSubCompanyService.query().subscribe((res: HttpResponse<IFmpSubCompanyMp[]>) => (this.fmpsubcompanies = res.body || []));
    });
  }

  updateForm(pushSubject: IPushSubjectMp): void {
    this.editForm.patchValue({
      id: pushSubject.id,
      agentId: pushSubject.agentId,
      name: pushSubject.name,
      agentGroupId: pushSubject.agentGroupId,
      titleColor: pushSubject.titleColor,
      remark: pushSubject.remark,
      fmpSubCompany: pushSubject.fmpSubCompany
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pushSubject = this.createFromForm();
    if (pushSubject.id !== undefined) {
      this.subscribeToSaveResponse(this.pushSubjectService.update(pushSubject));
    } else {
      this.subscribeToSaveResponse(this.pushSubjectService.create(pushSubject));
    }
  }

  private createFromForm(): IPushSubjectMp {
    return {
      ...new PushSubjectMp(),
      id: this.editForm.get(['id'])!.value,
      agentId: this.editForm.get(['agentId'])!.value,
      name: this.editForm.get(['name'])!.value,
      agentGroupId: this.editForm.get(['agentGroupId'])!.value,
      titleColor: this.editForm.get(['titleColor'])!.value,
      remark: this.editForm.get(['remark'])!.value,
      fmpSubCompany: this.editForm.get(['fmpSubCompany'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPushSubjectMp>>): void {
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

  trackById(index: number, item: IFmpSubCompanyMp): any {
    return item.id;
  }
}
