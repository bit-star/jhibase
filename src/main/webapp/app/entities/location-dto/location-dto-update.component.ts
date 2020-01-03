import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ILocationDTO, LocationDTO } from 'app/shared/model/location-dto.model';
import { LocationDTOService } from './location-dto.service';

@Component({
  selector: 'jhi-location-dto-update',
  templateUrl: './location-dto-update.component.html'
})
export class LocationDTOUpdateComponent implements OnInit {
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

  constructor(protected locationDTOService: LocationDTOService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ locationDTO }) => {
      this.updateForm(locationDTO);
    });
  }

  updateForm(locationDTO: ILocationDTO): void {
    this.editForm.patchValue({
      id: locationDTO.id,
      stationId: locationDTO.stationId,
      groupName: locationDTO.groupName,
      stationName: locationDTO.stationName,
      longitude: locationDTO.longitude,
      latitude: locationDTO.latitude,
      accuracy: locationDTO.accuracy,
      address: locationDTO.address,
      province: locationDTO.province,
      city: locationDTO.city,
      district: locationDTO.district,
      road: locationDTO.road,
      netType: locationDTO.netType,
      operatorType: locationDTO.operatorType,
      ddUserName: locationDTO.ddUserName,
      ddUserId: locationDTO.ddUserId,
      ddUserPhone: locationDTO.ddUserPhone,
      isFinish: locationDTO.isFinish,
      workLogMainId: locationDTO.workLogMainId,
      workLogType: locationDTO.workLogType,
      createdDate: locationDTO.createdDate != null ? locationDTO.createdDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const locationDTO = this.createFromForm();
    if (locationDTO.id !== undefined) {
      this.subscribeToSaveResponse(this.locationDTOService.update(locationDTO));
    } else {
      this.subscribeToSaveResponse(this.locationDTOService.create(locationDTO));
    }
  }

  private createFromForm(): ILocationDTO {
    return {
      ...new LocationDTO(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILocationDTO>>): void {
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
