import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICspaceFile, CspaceFile } from 'app/shared/model/cspace-file.model';
import { CspaceFileService } from './cspace-file.service';
import { IGovernmentReport } from 'app/shared/model/government-report.model';
import { GovernmentReportService } from 'app/entities/government-report/government-report.service';

@Component({
  selector: 'jhi-cspace-file-update',
  templateUrl: './cspace-file-update.component.html'
})
export class CspaceFileUpdateComponent implements OnInit {
  isSaving: boolean;

  governmentreports: IGovernmentReport[];

  editForm = this.fb.group({
    id: [],
    spaceId: [],
    fileId: [],
    fileName: [],
    fileSize: [],
    fileType: [],
    governmentReport: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected cspaceFileService: CspaceFileService,
    protected governmentReportService: GovernmentReportService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ cspaceFile }) => {
      this.updateForm(cspaceFile);
    });
    this.governmentReportService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IGovernmentReport[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGovernmentReport[]>) => response.body)
      )
      .subscribe((res: IGovernmentReport[]) => (this.governmentreports = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(cspaceFile: ICspaceFile) {
    this.editForm.patchValue({
      id: cspaceFile.id,
      spaceId: cspaceFile.spaceId,
      fileId: cspaceFile.fileId,
      fileName: cspaceFile.fileName,
      fileSize: cspaceFile.fileSize,
      fileType: cspaceFile.fileType,
      governmentReport: cspaceFile.governmentReport
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const cspaceFile = this.createFromForm();
    if (cspaceFile.id !== undefined) {
      this.subscribeToSaveResponse(this.cspaceFileService.update(cspaceFile));
    } else {
      this.subscribeToSaveResponse(this.cspaceFileService.create(cspaceFile));
    }
  }

  private createFromForm(): ICspaceFile {
    return {
      ...new CspaceFile(),
      id: this.editForm.get(['id']).value,
      spaceId: this.editForm.get(['spaceId']).value,
      fileId: this.editForm.get(['fileId']).value,
      fileName: this.editForm.get(['fileName']).value,
      fileSize: this.editForm.get(['fileSize']).value,
      fileType: this.editForm.get(['fileType']).value,
      governmentReport: this.editForm.get(['governmentReport']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICspaceFile>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackGovernmentReportById(index: number, item: IGovernmentReport) {
    return item.id;
  }
}
