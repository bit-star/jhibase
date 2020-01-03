import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'user-product',
        loadChildren: () => import('./user-product/user-product.module').then(m => m.JhibaseUserProductModule)
      },
      {
        path: 'cspace-file',
        loadChildren: () => import('./cspace-file/cspace-file.module').then(m => m.JhibaseCspaceFileModule)
      },
      {
        path: 'government-report',
        loadChildren: () => import('./government-report/government-report.module').then(m => m.JhibaseGovernmentReportModule)
      },
      {
        path: 'history-search',
        loadChildren: () => import('./history-search/history-search.module').then(m => m.JhibaseHistorySearchModule)
      },
      {
        path: 'location-dto',
        loadChildren: () => import('./location-dto/location-dto.module').then(m => m.JhibaseLocationDTOModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhibaseEntityModule {}
