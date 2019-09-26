import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGovernmentReport } from 'app/shared/model/government-report.model';

@Component({
  selector: 'jhi-government-report-detail',
  templateUrl: './government-report-detail.component.html'
})
export class GovernmentReportDetailComponent implements OnInit {
  governmentReport: IGovernmentReport;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ governmentReport }) => {
      this.governmentReport = governmentReport;
    });
  }

  previousState() {
    window.history.back();
  }
}
