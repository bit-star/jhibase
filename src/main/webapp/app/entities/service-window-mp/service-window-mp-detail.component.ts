import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IServiceWindowMp } from 'app/shared/model/service-window-mp.model';

@Component({
  selector: 'jhi-service-window-mp-detail',
  templateUrl: './service-window-mp-detail.component.html'
})
export class ServiceWindowMpDetailComponent implements OnInit {
  serviceWindow: IServiceWindowMp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ serviceWindow }) => (this.serviceWindow = serviceWindow));
  }

  previousState(): void {
    window.history.back();
  }
}
