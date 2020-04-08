import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { UucUserBaseinfoMpComponent } from './uuc-user-baseinfo-mp.component';
import { UucUserBaseinfoMpDetailComponent } from './uuc-user-baseinfo-mp-detail.component';
import { UucUserBaseinfoMpUpdateComponent } from './uuc-user-baseinfo-mp-update.component';
import { UucUserBaseinfoMpDeleteDialogComponent } from './uuc-user-baseinfo-mp-delete-dialog.component';
import { uucUserBaseinfoRoute } from './uuc-user-baseinfo-mp.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(uucUserBaseinfoRoute)],
  declarations: [
    UucUserBaseinfoMpComponent,
    UucUserBaseinfoMpDetailComponent,
    UucUserBaseinfoMpUpdateComponent,
    UucUserBaseinfoMpDeleteDialogComponent
  ],
  entryComponents: [UucUserBaseinfoMpDeleteDialogComponent]
})
export class JhibaseUucUserBaseinfoMpModule {}
