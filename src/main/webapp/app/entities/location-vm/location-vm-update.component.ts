import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILocationVM, LocationVM } from 'app/shared/model/location-vm.model';
import { LocationVMService } from './location-vm.service';

@Component({
  selector: 'jhi-location-vm-update',
  templateUrl: './location-vm-update.component.html'
})
export class LocationVMUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    stationId: [],
    groupName: [],
    stationName: [],
    longitude: [],
    latitude: [],
    accuracy: [],
    address: [],
    province: [],
    city: [],
    district: [],
    road: [],
    netType: [],
    operatorType: [],
    ddUserName: [],
    ddUserId: [],
    ddUserPhone: [],
    isFinish: [],
    workLogMainId: [],
    workLogType: [],
    createdDate: []
  });

  constructor(protected locationVMService: LocationVMService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ locationVM }) => {
      this.updateForm(locationVM);
    });
  }

  updateForm(locationVM: ILocationVM): void {
    this.editForm.patchValue({
      id: locationVM.id,
      stationId: locationVM.stationId,
      groupName: locationVM.groupName,
      stationName: locationVM.stationName,
      longitude: locationVM.longitude,
      latitude: locationVM.latitude,
      accuracy: locationVM.accuracy,
      address: locationVM.address,
      province: locationVM.province,
      city: locationVM.city,
      district: locationVM.district,
      road: locationVM.road,
      netType: locationVM.netType,
      operatorType: locationVM.operatorType,
      ddUserName: locationVM.ddUserName,
      ddUserId: locationVM.ddUserId,
      ddUserPhone: locationVM.ddUserPhone,
      isFinish: locationVM.isFinish,
      workLogMainId: locationVM.workLogMainId,
      workLogType: locationVM.workLogType,
      createdDate: locationVM.createdDate != null ? locationVM.createdDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const locationVM = this.createFromForm();
    if (locationVM.id !== undefined) {
      this.subscribeToSaveResponse(this.locationVMService.update(locationVM));
    } else {
      this.subscribeToSaveResponse(this.locationVMService.create(locationVM));
    }
  }

  private createFromForm(): ILocationVM {
    return {
      ...new LocationVM(),
      id: this.editForm.get(['id'])!.value,
      stationId: this.editForm.get(['stationId'])!.value,
      groupName: this.editForm.get(['groupName'])!.value,
      stationName: this.editForm.get(['stationName'])!.value,
      longitude: this.editForm.get(['longitude'])!.value,
      latitude: this.editForm.get(['latitude'])!.value,
      accuracy: this.editForm.get(['accuracy'])!.value,
      address: this.editForm.get(['address'])!.value,
      province: this.editForm.get(['province'])!.value,
      city: this.editForm.get(['city'])!.value,
      district: this.editForm.get(['district'])!.value,
      road: this.editForm.get(['road'])!.value,
      netType: this.editForm.get(['netType'])!.value,
      operatorType: this.editForm.get(['operatorType'])!.value,
      ddUserName: this.editForm.get(['ddUserName'])!.value,
      ddUserId: this.editForm.get(['ddUserId'])!.value,
      ddUserPhone: this.editForm.get(['ddUserPhone'])!.value,
      isFinish: this.editForm.get(['isFinish'])!.value,
      workLogMainId: this.editForm.get(['workLogMainId'])!.value,
      workLogType: this.editForm.get(['workLogType'])!.value,
      createdDate:
        this.editForm.get(['createdDate'])!.value != null ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT) : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocationVM>>): void {
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
