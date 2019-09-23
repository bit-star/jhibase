import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { UserProductDetailComponent } from 'app/entities/user-product/user-product-detail.component';
import { UserProduct } from 'app/shared/model/user-product.model';

describe('Component Tests', () => {
  describe('UserProduct Management Detail Component', () => {
    let comp: UserProductDetailComponent;
    let fixture: ComponentFixture<UserProductDetailComponent>;
    const route = ({ data: of({ userProduct: new UserProduct(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UserProductDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UserProductDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserProductDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.userProduct).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
