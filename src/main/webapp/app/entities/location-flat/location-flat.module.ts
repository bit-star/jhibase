import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { LocationFlatComponent } from './location-flat.component';
import { LocationFlatDetailComponent } from './location-flat-detail.component';
import { LocationFlatUpdateComponent } from './location-flat-update.component';
import { LocationFlatDeleteDialogComponent } from './location-flat-delete-dialog.component';
import { locationFlatRoute } from './location-flat.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(locationFlatRoute)],
  declarations: [LocationFlatComponent, LocationFlatDetailComponent, LocationFlatUpdateComponent, LocationFlatDeleteDialogComponent],
  entryComponents: [LocationFlatDeleteDialogComponent]
})
export class JhibaseLocationFlatModule {}
