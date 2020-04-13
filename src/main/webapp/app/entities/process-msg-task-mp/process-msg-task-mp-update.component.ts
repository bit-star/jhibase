import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProcessMsgTaskMp, ProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';
import { ProcessMsgTaskMpService } from './process-msg-task-mp.service';
import { IPushSubjectMp } from 'app/shared/model/push-subject-mp.model';
import { PushSubjectMpService } from 'app/entities/push-subject-mp/push-subject-mp.service';

@Component({
  selector: 'jhi-process-msg-task-mp-update',
  templateUrl: './process-msg-task-mp-update.component.html'
})
export class ProcessMsgTaskMpUpdateComponent implements OnInit {
  isSaving = false;
  pushsubjects: IPushSubjectMp[] = [];

  editForm = this.fb.group({
    id: [],
    pushSubject: []
  });

  constructor(
    protected processMsgTaskService: ProcessMsgTaskMpService,
    protected pushSubjectService: PushSubjectMpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ processMsgTask }) => {
      this.updateForm(processMsgTask);

      this.pushSubjectService.query().subscribe((res: HttpResponse<IPushSubjectMp[]>) => (this.pushsubjects = res.body || []));
    });
  }

  updateForm(processMsgTask: IProcessMsgTaskMp): void {
    this.editForm.patchValue({
      id: processMsgTask.id,
      pushSubject: processMsgTask.pushSubject
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const processMsgTask = this.createFromForm();
    if (processMsgTask.id !== undefined) {
      this.subscribeToSaveResponse(this.processMsgTaskService.update(processMsgTask));
    } else {
      this.subscribeToSaveResponse(this.processMsgTaskService.create(processMsgTask));
    }
  }

  private createFromForm(): IProcessMsgTaskMp {
    return {
      ...new ProcessMsgTaskMp(),
      id: this.editForm.get(['id'])!.value,
      pushSubject: this.editForm.get(['pushSubject'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProcessMsgTaskMp>>): void {
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

  trackById(index: number, item: IPushSubjectMp): any {
    return item.id;
  }
}
