import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMsgReceiverGroupMp, MsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';
import { MsgReceiverGroupMpService } from './msg-receiver-group-mp.service';
import { IUucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';
import { UucDepartmentTreeMpService } from 'app/entities/uuc-department-tree-mp/uuc-department-tree-mp.service';
import { IUucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';
import { UucUserBaseinfoMpService } from 'app/entities/uuc-user-baseinfo-mp/uuc-user-baseinfo-mp.service';
import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { FmpSubCompanyMpService } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp.service';

type SelectableEntity = IUucDepartmentTreeMp | IUucUserBaseinfoMp | IFmpSubCompanyMp;

type SelectableManyToManyEntity = IUucDepartmentTreeMp | IUucUserBaseinfoMp;

@Component({
  selector: 'jhi-msg-receiver-group-mp-update',
  templateUrl: './msg-receiver-group-mp-update.component.html'
})
export class MsgReceiverGroupMpUpdateComponent implements OnInit {
  isSaving = false;
  uucdepartmenttrees: IUucDepartmentTreeMp[] = [];
  uucuserbaseinfos: IUucUserBaseinfoMp[] = [];
  fmpsubcompanies: IFmpSubCompanyMp[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    desc: [],
    uucDepartmentTrees: [],
    uucUserBaseinfos: [],
    fmpSubCompany: []
  });

  constructor(
    protected msgReceiverGroupService: MsgReceiverGroupMpService,
    protected uucDepartmentTreeService: UucDepartmentTreeMpService,
    protected uucUserBaseinfoService: UucUserBaseinfoMpService,
    protected fmpSubCompanyService: FmpSubCompanyMpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ msgReceiverGroup }) => {
      this.updateForm(msgReceiverGroup);

      this.uucDepartmentTreeService
        .query()
        .subscribe((res: HttpResponse<IUucDepartmentTreeMp[]>) => (this.uucdepartmenttrees = res.body || []));

      this.uucUserBaseinfoService.query().subscribe((res: HttpResponse<IUucUserBaseinfoMp[]>) => (this.uucuserbaseinfos = res.body || []));

      this.fmpSubCompanyService.query().subscribe((res: HttpResponse<IFmpSubCompanyMp[]>) => (this.fmpsubcompanies = res.body || []));
    });
  }

  updateForm(msgReceiverGroup: IMsgReceiverGroupMp): void {
    this.editForm.patchValue({
      id: msgReceiverGroup.id,
      name: msgReceiverGroup.name,
      desc: msgReceiverGroup.desc,
      uucDepartmentTrees: msgReceiverGroup.uucDepartmentTrees,
      uucUserBaseinfos: msgReceiverGroup.uucUserBaseinfos,
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
      uucDepartmentTrees: this.editForm.get(['uucDepartmentTrees'])!.value,
      uucUserBaseinfos: this.editForm.get(['uucUserBaseinfos'])!.value,
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: SelectableManyToManyEntity[], option: SelectableManyToManyEntity): SelectableManyToManyEntity {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
