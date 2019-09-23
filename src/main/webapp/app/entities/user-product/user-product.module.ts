import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhibaseSharedModule } from 'app/shared/shared.module';
import { UserProductComponent } from './user-product.component';
import { UserProductDetailComponent } from './user-product-detail.component';
import { UserProductUpdateComponent } from './user-product-update.component';
import { UserProductDeletePopupComponent, UserProductDeleteDialogComponent } from './user-product-delete-dialog.component';
import { userProductRoute, userProductPopupRoute } from './user-product.route';

const ENTITY_STATES = [...userProductRoute, ...userProductPopupRoute];

@NgModule({
  imports: [JhibaseSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    UserProductComponent,
    UserProductDetailComponent,
    UserProductUpdateComponent,
    UserProductDeleteDialogComponent,
    UserProductDeletePopupComponent
  ],
  entryComponents: [UserProductDeleteDialogComponent]
})
export class JhibaseUserProductModule {}
