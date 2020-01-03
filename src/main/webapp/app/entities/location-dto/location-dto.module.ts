import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { LocationDTOComponent } from './location-dto.component';
import { LocationDTODetailComponent } from './location-dto-detail.component';
import { LocationDTOUpdateComponent } from './location-dto-update.component';
import { LocationDTODeleteDialogComponent } from './location-dto-delete-dialog.component';
import { locationDTORoute } from './location-dto.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(locationDTORoute)],
  declarations: [LocationDTOComponent, LocationDTODetailComponent, LocationDTOUpdateComponent, LocationDTODeleteDialogComponent],
  entryComponents: [LocationDTODeleteDialogComponent]
})
export class JhibaseLocationDTOModule {}
