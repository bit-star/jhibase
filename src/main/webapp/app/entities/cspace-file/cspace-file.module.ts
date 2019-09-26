import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { CspaceFileComponent } from './cspace-file.component';
import { CspaceFileDetailComponent } from './cspace-file-detail.component';
import { CspaceFileUpdateComponent } from './cspace-file-update.component';
import { CspaceFileDeletePopupComponent, CspaceFileDeleteDialogComponent } from './cspace-file-delete-dialog.component';
import { cspaceFileRoute, cspaceFilePopupRoute } from './cspace-file.route';

const ENTITY_STATES = [...cspaceFileRoute, ...cspaceFilePopupRoute];

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    CspaceFileComponent,
    CspaceFileDetailComponent,
    CspaceFileUpdateComponent,
    CspaceFileDeleteDialogComponent,
    CspaceFileDeletePopupComponent
  ],
  entryComponents: [CspaceFileDeleteDialogComponent]
})
export class JhibaseCspaceFileModule {}
