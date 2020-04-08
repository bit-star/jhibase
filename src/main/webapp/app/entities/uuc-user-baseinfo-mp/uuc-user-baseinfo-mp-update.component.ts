import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUucUserBaseinfoMp, UucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';
import { UucUserBaseinfoMpService } from './uuc-user-baseinfo-mp.service';
import { IMsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';
import { MsgReceiverGroupMpService } from 'app/entities/msg-receiver-group-mp/msg-receiver-group-mp.service';

@Component({
  selector: 'jhi-uuc-user-baseinfo-mp-update',
  templateUrl: './uuc-user-baseinfo-mp-update.component.html'
})
export class UucUserBaseinfoMpUpdateComponent implements OnInit {
  isSaving = false;
  msgreceivergroups: IMsgReceiverGroupMp[] = [];

  editForm = this.fb.group({
    id: [],
    msgReceiverGroup: []
  });

  constructor(
    protected uucUserBaseinfoService: UucUserBaseinfoMpService,
    protected msgReceiverGroupService: MsgReceiverGroupMpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ uucUserBaseinfo }) => {
      this.updateForm(uucUserBaseinfo);

      this.msgReceiverGroupService
        .query()
        .subscribe((res: HttpResponse<IMsgReceiverGroupMp[]>) => (this.msgreceivergroups = res.body || []));
    });
  }

  updateForm(uucUserBaseinfo: IUucUserBaseinfoMp): void {
    this.editForm.patchValue({
      id: uucUserBaseinfo.id,
      msgReceiverGroup: uucUserBaseinfo.msgReceiverGroup
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const uucUserBaseinfo = this.createFromForm();
    if (uucUserBaseinfo.id !== undefined) {
      this.subscribeToSaveResponse(this.uucUserBaseinfoService.update(uucUserBaseinfo));
    } else {
      this.subscribeToSaveResponse(this.uucUserBaseinfoService.create(uucUserBaseinfo));
    }
  }

  private createFromForm(): IUucUserBaseinfoMp {
    return {
      ...new UucUserBaseinfoMp(),
      id: this.editForm.get(['id'])!.value,
      msgReceiverGroup: this.editForm.get(['msgReceiverGroup'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUucUserBaseinfoMp>>): void {
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

  trackById(index: number, item: IMsgReceiverGroupMp): any {
    return item.id;
  }
}
