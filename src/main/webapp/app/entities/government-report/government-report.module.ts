import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { GovernmentReportComponent } from './government-report.component';
import { GovernmentReportDetailComponent } from './government-report-detail.component';
import { GovernmentReportUpdateComponent } from './government-report-update.component';
import { GovernmentReportDeletePopupComponent, GovernmentReportDeleteDialogComponent } from './government-report-delete-dialog.component';
import { governmentReportRoute, governmentReportPopupRoute } from './government-report.route';

const ENTITY_STATES = [...governmentReportRoute, ...governmentReportPopupRoute];

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    GovernmentReportComponent,
    GovernmentReportDetailComponent,
    GovernmentReportUpdateComponent,
    GovernmentReportDeleteDialogComponent,
    GovernmentReportDeletePopupComponent
  ],
  entryComponents: [GovernmentReportDeleteDialogComponent]
})
export class JhibaseGovernmentReportModule {}
