import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { ProcessMsgTaskMpComponent } from './process-msg-task-mp.component';
import { ProcessMsgTaskMpDetailComponent } from './process-msg-task-mp-detail.component';
import { ProcessMsgTaskMpUpdateComponent } from './process-msg-task-mp-update.component';
import { ProcessMsgTaskMpDeleteDialogComponent } from './process-msg-task-mp-delete-dialog.component';
import { processMsgTaskRoute } from './process-msg-task-mp.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(processMsgTaskRoute)],
  declarations: [
    ProcessMsgTaskMpComponent,
    ProcessMsgTaskMpDetailComponent,
    ProcessMsgTaskMpUpdateComponent,
    ProcessMsgTaskMpDeleteDialogComponent
  ],
  entryComponents: [ProcessMsgTaskMpDeleteDialogComponent]
})
export class JhibaseProcessMsgTaskMpModule {}
