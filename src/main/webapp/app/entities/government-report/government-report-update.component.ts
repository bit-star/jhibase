import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IGovernmentReport, GovernmentReport } from 'app/shared/model/government-report.model';
import { GovernmentReportService } from './government-report.service';

@Component({
  selector: 'jhi-government-report-update',
  templateUrl: './government-report-update.component.html'
})
export class GovernmentReportUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    type: [],
    reportDate: [],
    objectName: [],
    position: [],
    contactInformation: [],
    content: [],
    location: []
  });

  constructor(
    protected governmentReportService: GovernmentReportService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ governmentReport }) => {
      this.updateForm(governmentReport);
    });
  }

  updateForm(governmentReport: IGovernmentReport) {
    this.editForm.patchValue({
      id: governmentReport.id,
      type: governmentReport.type,
      reportDate: governmentReport.reportDate != null ? governmentReport.reportDate.format(DATE_TIME_FORMAT) : null,
      objectName: governmentReport.objectName,
      position: governmentReport.position,
      contactInformation: governmentReport.contactInformation,
      content: governmentReport.content,
      location: governmentReport.location
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const governmentReport = this.createFromForm();
    if (governmentReport.id !== undefined) {
      this.subscribeToSaveResponse(this.governmentReportService.update(governmentReport));
    } else {
      this.subscribeToSaveResponse(this.governmentReportService.create(governmentReport));
    }
  }

  private createFromForm(): IGovernmentReport {
    return {
      ...new GovernmentReport(),
      id: this.editForm.get(['id']).value,
      type: this.editForm.get(['type']).value,
      reportDate:
        this.editForm.get(['reportDate']).value != null ? moment(this.editForm.get(['reportDate']).value, DATE_TIME_FORMAT) : undefined,
      objectName: this.editForm.get(['objectName']).value,
      position: this.editForm.get(['position']).value,
      contactInformation: this.editForm.get(['contactInformation']).value,
      content: this.editForm.get(['content']).value,
      location: this.editForm.get(['location']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGovernmentReport>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
