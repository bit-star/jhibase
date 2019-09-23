import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserProduct } from 'app/shared/model/user-product.model';

@Component({
  selector: 'jhi-user-product-detail',
  templateUrl: './user-product-detail.component.html'
})
export class UserProductDetailComponent implements OnInit {
  userProduct: IUserProduct;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ userProduct }) => {
      this.userProduct = userProduct;
    });
  }

  previousState() {
    window.history.back();
  }
}
