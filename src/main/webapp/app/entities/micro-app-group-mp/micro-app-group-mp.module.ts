import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { MicroAppGroupMpComponent } from './micro-app-group-mp.component';
import { MicroAppGroupMpDetailComponent } from './micro-app-group-mp-detail.component';
import { MicroAppGroupMpUpdateComponent } from './micro-app-group-mp-update.component';
import { MicroAppGroupMpDeleteDialogComponent } from './micro-app-group-mp-delete-dialog.component';
import { microAppGroupRoute } from './micro-app-group-mp.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(microAppGroupRoute)],
  declarations: [
    MicroAppGroupMpComponent,
    MicroAppGroupMpDetailComponent,
    MicroAppGroupMpUpdateComponent,
    MicroAppGroupMpDeleteDialogComponent,
  ],
  entryComponents: [MicroAppGroupMpDeleteDialogComponent],
})
export class JhibaseMicroAppGroupMpModule {}
