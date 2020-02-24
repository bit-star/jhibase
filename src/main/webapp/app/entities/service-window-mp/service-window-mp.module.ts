import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { ServiceWindowMpComponent } from './service-window-mp.component';
import { ServiceWindowMpDetailComponent } from './service-window-mp-detail.component';
import { ServiceWindowMpUpdateComponent } from './service-window-mp-update.component';
import { ServiceWindowMpDeleteDialogComponent } from './service-window-mp-delete-dialog.component';
import { serviceWindowRoute } from './service-window-mp.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(serviceWindowRoute)],
  declarations: [
    ServiceWindowMpComponent,
    ServiceWindowMpDetailComponent,
    ServiceWindowMpUpdateComponent,
    ServiceWindowMpDeleteDialogComponent
  ],
  entryComponents: [ServiceWindowMpDeleteDialogComponent]
})
export class JhibaseServiceWindowMpModule {}
