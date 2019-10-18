import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { HistorySearchComponent } from './history-search.component';
import { HistorySearchDetailComponent } from './history-search-detail.component';
import { HistorySearchUpdateComponent } from './history-search-update.component';
import { HistorySearchDeletePopupComponent, HistorySearchDeleteDialogComponent } from './history-search-delete-dialog.component';
import { historySearchRoute, historySearchPopupRoute } from './history-search.route';

const ENTITY_STATES = [...historySearchRoute, ...historySearchPopupRoute];

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    HistorySearchComponent,
    HistorySearchDetailComponent,
    HistorySearchUpdateComponent,
    HistorySearchDeleteDialogComponent,
    HistorySearchDeletePopupComponent
  ],
  entryComponents: [HistorySearchDeleteDialogComponent]
})
export class JhibaseHistorySearchModule {}
