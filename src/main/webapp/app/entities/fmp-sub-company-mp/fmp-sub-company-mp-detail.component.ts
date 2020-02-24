import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';

@Component({
  selector: 'jhi-fmp-sub-company-mp-detail',
  templateUrl: './fmp-sub-company-mp-detail.component.html'
})
export class FmpSubCompanyMpDetailComponent implements OnInit {
  fmpSubCompany: IFmpSubCompanyMp | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fmpSubCompany }) => (this.fmpSubCompany = fmpSubCompany));
  }

  previousState(): void {
    window.history.back();
  }
}
