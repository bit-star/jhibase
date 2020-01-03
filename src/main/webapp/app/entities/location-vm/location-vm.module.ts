import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { LocationVMComponent } from './location-vm.component';
import { LocationVMDetailComponent } from './location-vm-detail.component';
import { LocationVMUpdateComponent } from './location-vm-update.component';
import { LocationVMDeleteDialogComponent } from './location-vm-delete-dialog.component';
import { locationVMRoute } from './location-vm.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(locationVMRoute)],
  declarations: [LocationVMComponent, LocationVMDetailComponent, LocationVMUpdateComponent, LocationVMDeleteDialogComponent],
  entryComponents: [LocationVMDeleteDialogComponent]
})
export class JhibaseLocationVMModule {}
