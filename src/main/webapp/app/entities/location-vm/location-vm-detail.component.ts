import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILocationVM } from 'app/shared/model/location-vm.model';

@Component({
  selector: 'jhi-location-vm-detail',
  templateUrl: './location-vm-detail.component.html'
})
export class LocationVMDetailComponent implements OnInit {
  locationVM: ILocationVM | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ locationVM }) => {
      this.locationVM = locationVM;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
