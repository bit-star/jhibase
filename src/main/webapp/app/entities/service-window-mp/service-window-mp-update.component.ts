import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IServiceWindowMp, ServiceWindowMp } from 'app/shared/model/service-window-mp.model';
import { ServiceWindowMpService } from './service-window-mp.service';
import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';
import { FmpSubCompanyMpService } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp.service';

@Component({
  selector: 'jhi-service-window-mp-update',
  templateUrl: './service-window-mp-update.component.html'
})
export class ServiceWindowMpUpdateComponent implements OnInit {
  isSaving = false;
  fmpsubcompanies: IFmpSubCompanyMp[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    userId: [],
    link: [],
    icon: [],
    rank: [],
    remark: [],
    status: [],
    fmpSubCompany: []
  });

  constructor(
    protected serviceWindowService: ServiceWindowMpService,
    protected fmpSubCompanyService: FmpSubCompanyMpService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ serviceWindow }) => {
      this.updateForm(serviceWindow);

      this.fmpSubCompanyService.query().subscribe((res: HttpResponse<IFmpSubCompanyMp[]>) => (this.fmpsubcompanies = res.body || []));
    });
  }

  updateForm(serviceWindow: IServiceWindowMp): void {
    this.editForm.patchValue({
      id: serviceWindow.id,
      name: serviceWindow.name,
      userId: serviceWindow.userId,
      link: serviceWindow.link,
      icon: serviceWindow.icon,
      rank: serviceWindow.rank,
      remark: serviceWindow.remark,
      status: serviceWindow.status,
      fmpSubCompany: serviceWindow.fmpSubCompany
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const serviceWindow = this.createFromForm();
    if (serviceWindow.id !== undefined) {
      this.subscribeToSaveResponse(this.serviceWindowService.update(serviceWindow));
    } else {
      this.subscribeToSaveResponse(this.serviceWindowService.create(serviceWindow));
    }
  }

  private createFromForm(): IServiceWindowMp {
    return {
      ...new ServiceWindowMp(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      link: this.editForm.get(['link'])!.value,
      icon: this.editForm.get(['icon'])!.value,
      rank: this.editForm.get(['rank'])!.value,
      remark: this.editForm.get(['remark'])!.value,
      status: this.editForm.get(['status'])!.value,
      fmpSubCompany: this.editForm.get(['fmpSubCompany'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServiceWindowMp>>): void {
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
