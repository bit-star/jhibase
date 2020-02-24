import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFmpSubCompanyMp, FmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { FmpSubCompanyMpService } from './fmp-sub-company-mp.service';

@Component({
  selector: 'jhi-fmp-sub-company-mp-update',
  templateUrl: './fmp-sub-company-mp-update.component.html'
})
export class FmpSubCompanyMpUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    code: [],
    adminGroupId: [],
    ifPublic: [],
    styleId: [],
    isDeleted: []
  });

  constructor(protected fmpSubCompanyService: FmpSubCompanyMpService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fmpSubCompany }) => {
      this.updateForm(fmpSubCompany);
    });
  }

  updateForm(fmpSubCompany: IFmpSubCompanyMp): void {
    this.editForm.patchValue({
      id: fmpSubCompany.id,
      name: fmpSubCompany.name,
      code: fmpSubCompany.code,
      adminGroupId: fmpSubCompany.adminGroupId,
      ifPublic: fmpSubCompany.ifPublic,
      styleId: fmpSubCompany.styleId,
      isDeleted: fmpSubCompany.isDeleted
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fmpSubCompany = this.createFromForm();
    if (fmpSubCompany.id !== undefined) {
      this.subscribeToSaveResponse(this.fmpSubCompanyService.update(fmpSubCompany));
    } else {
      this.subscribeToSaveResponse(this.fmpSubCompanyService.create(fmpSubCompany));
    }
  }

  private createFromForm(): IFmpSubCompanyMp {
    return {
      ...new FmpSubCompanyMp(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      code: this.editForm.get(['code'])!.value,
      adminGroupId: this.editForm.get(['adminGroupId'])!.value,
      ifPublic: this.editForm.get(['ifPublic'])!.value,
      styleId: this.editForm.get(['styleId'])!.value,
      isDeleted: this.editForm.get(['isDeleted'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFmpSubCompanyMp>>): void {
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
