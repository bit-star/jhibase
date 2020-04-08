import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMsgReceiverGroupMp, MsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';
import { MsgReceiverGroupMpService } from './msg-receiver-group-mp.service';
import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { FmpSubCompanyMpService } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp.service';

@Component({
  selector: 'jhi-msg-receiver-group-mp-update',
  templateUrl: './msg-receiver-group-mp-update.component.html'
})
export class MsgReceiverGroupMpUpdateComponent implements OnInit {
  isSaving = false;
  fmpsubcompanies: IFmpSubCompanyMp[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    desc: [],
    fmpSubCompany: []
  });

  constructor(
    protected msgReceiverGroupService: MsgReceiverGroupMpService,
    protected fmpSubCompanyService: FmpSubCompanyMpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ msgReceiverGroup }) => {
      this.updateForm(msgReceiverGroup);

      this.fmpSubCompanyService.query().subscribe((res: HttpResponse<IFmpSubCompanyMp[]>) => (this.fmpsubcompanies = res.body || []));
    });
  }

  updateForm(msgReceiverGroup: IMsgReceiverGroupMp): void {
    this.editForm.patchValue({
      id: msgReceiverGroup.id,
      name: msgReceiverGroup.name,
      desc: msgReceiverGroup.desc,
      fmpSubCompany: msgReceiverGroup.fmpSubCompany
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const msgReceiverGroup = this.createFromForm();
    if (msgReceiverGroup.id !== undefined) {
      this.subscribeToSaveResponse(this.msgReceiverGroupService.update(msgReceiverGroup));
    } else {
      this.subscribeToSaveResponse(this.msgReceiverGroupService.create(msgReceiverGroup));
    }
  }

  private createFromForm(): IMsgReceiverGroupMp {
    return {
      ...new MsgReceiverGroupMp(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      desc: this.editForm.get(['desc'])!.value,
      fmpSubCompany: this.editForm.get(['fmpSubCompany'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMsgReceiverGroupMp>>): void {
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
