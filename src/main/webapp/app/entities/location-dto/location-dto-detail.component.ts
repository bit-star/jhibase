import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILocationDTO } from 'app/shared/model/location-dto.model';

@Component({
  selector: 'jhi-location-dto-detail',
  templateUrl: './location-dto-detail.component.html'
})
export class LocationDTODetailComponent implements OnInit {
  locationDTO: ILocationDTO | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ locationDTO }) => {
      this.locationDTO = locationDTO;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
