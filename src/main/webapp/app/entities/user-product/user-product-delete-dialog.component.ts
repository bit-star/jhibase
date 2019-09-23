import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserProduct } from 'app/shared/model/user-product.model';
import { UserProductService } from './user-product.service';

@Component({
  selector: 'jhi-user-product-delete-dialog',
  templateUrl: './user-product-delete-dialog.component.html'
})
export class UserProductDeleteDialogComponent {
  userProduct: IUserProduct;

  constructor(
    protected userProductService: UserProductService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.userProductService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'userProductListModification',
        content: 'Deleted an userProduct'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-user-product-delete-popup',
  template: ''
})
export class UserProductDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userProduct }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(UserProductDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.userProduct = userProduct;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/user-product', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/user-product', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
