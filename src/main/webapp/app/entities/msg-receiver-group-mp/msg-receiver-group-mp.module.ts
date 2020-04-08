import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { MsgReceiverGroupMpComponent } from './msg-receiver-group-mp.component';
import { MsgReceiverGroupMpDetailComponent } from './msg-receiver-group-mp-detail.component';
import { MsgReceiverGroupMpUpdateComponent } from './msg-receiver-group-mp-update.component';
import { MsgReceiverGroupMpDeleteDialogComponent } from './msg-receiver-group-mp-delete-dialog.component';
import { msgReceiverGroupRoute } from './msg-receiver-group-mp.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(msgReceiverGroupRoute)],
  declarations: [
    MsgReceiverGroupMpComponent,
    MsgReceiverGroupMpDetailComponent,
    MsgReceiverGroupMpUpdateComponent,
    MsgReceiverGroupMpDeleteDialogComponent
  ],
  entryComponents: [MsgReceiverGroupMpDeleteDialogComponent]
})
export class JhibaseMsgReceiverGroupMpModule {}
