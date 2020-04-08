import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { PushSubjectMpComponent } from './push-subject-mp.component';
import { PushSubjectMpDetailComponent } from './push-subject-mp-detail.component';
import { PushSubjectMpUpdateComponent } from './push-subject-mp-update.component';
import { PushSubjectMpDeleteDialogComponent } from './push-subject-mp-delete-dialog.component';
import { pushSubjectRoute } from './push-subject-mp.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(pushSubjectRoute)],
  declarations: [PushSubjectMpComponent, PushSubjectMpDetailComponent, PushSubjectMpUpdateComponent, PushSubjectMpDeleteDialogComponent],
  entryComponents: [PushSubjectMpDeleteDialogComponent]
})
export class JhibasePushSubjectMpModule {}
