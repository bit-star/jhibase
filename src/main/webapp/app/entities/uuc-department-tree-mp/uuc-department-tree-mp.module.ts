import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { UucDepartmentTreeMpComponent } from './uuc-department-tree-mp.component';
import { UucDepartmentTreeMpDetailComponent } from './uuc-department-tree-mp-detail.component';
import { UucDepartmentTreeMpUpdateComponent } from './uuc-department-tree-mp-update.component';
import { UucDepartmentTreeMpDeleteDialogComponent } from './uuc-department-tree-mp-delete-dialog.component';
import { uucDepartmentTreeRoute } from './uuc-department-tree-mp.route';

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(uucDepartmentTreeRoute)],
  declarations: [
    UucDepartmentTreeMpComponent,
    UucDepartmentTreeMpDetailComponent,
    UucDepartmentTreeMpUpdateComponent,
    UucDepartmentTreeMpDeleteDialogComponent
  ],
  entryComponents: [UucDepartmentTreeMpDeleteDialogComponent]
})
export class JhibaseUucDepartmentTreeMpModule {}
