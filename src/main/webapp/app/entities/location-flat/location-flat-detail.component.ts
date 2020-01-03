import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILocationFlat } from 'app/shared/model/location-flat.model';

@Component({
  selector: 'jhi-location-flat-detail',
  templateUrl: './location-flat-detail.component.html'
})
export class LocationFlatDetailComponent implements OnInit {
  locationFlat: ILocationFlat | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ locationFlat }) => {
      this.locationFlat = locationFlat;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
