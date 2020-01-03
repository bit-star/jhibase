import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILocationFlat, LocationFlat } from 'app/shared/model/location-flat.model';
import { LocationFlatService } from './location-flat.service';

@Component({
  selector: 'jhi-location-flat-update',
  templateUrl: './location-flat-update.component.html'
})
export class LocationFlatUpdateComponent implements OnInit {
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

  constructor(protected locationFlatService: LocationFlatService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ locationFlat }) => {
      this.updateForm(locationFlat);
    });
  }

  updateForm(locationFlat: ILocationFlat): void {
    this.editForm.patchValue({
      id: locationFlat.id,
      stationId: locationFlat.stationId,
      groupName: locationFlat.groupName,
      stationName: locationFlat.stationName,
      longitude: locationFlat.longitude,
      latitude: locationFlat.latitude,
      accuracy: locationFlat.accuracy,
      address: locationFlat.address,
      province: locationFlat.province,
      city: locationFlat.city,
      district: locationFlat.district,
      road: locationFlat.road,
      netType: locationFlat.netType,
      operatorType: locationFlat.operatorType,
      ddUserName: locationFlat.ddUserName,
      ddUserId: locationFlat.ddUserId,
      ddUserPhone: locationFlat.ddUserPhone,
      isFinish: locationFlat.isFinish,
      workLogMainId: locationFlat.workLogMainId,
      workLogType: locationFlat.workLogType,
      createdDate: locationFlat.createdDate != null ? locationFlat.createdDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const locationFlat = this.createFromForm();
    if (locationFlat.id !== undefined) {
      this.subscribeToSaveResponse(this.locationFlatService.update(locationFlat));
    } else {
      this.subscribeToSaveResponse(this.locationFlatService.create(locationFlat));
    }
  }

  private createFromForm(): ILocationFlat {
    return {
      ...new LocationFlat(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocationFlat>>): void {
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
