import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMicroAppGroupMp } from 'app/shared/model/micro-app-group-mp.model';

@Component({
  selector: 'jhi-micro-app-group-mp-detail',
  templateUrl: './micro-app-group-mp-detail.component.html',
})
export class MicroAppGroupMpDetailComponent implements OnInit {
  microAppGroup: IMicroAppGroupMp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ microAppGroup }) => (this.microAppGroup = microAppGroup));
  }

  previousState(): void {
    window.history.back();
  }
}
