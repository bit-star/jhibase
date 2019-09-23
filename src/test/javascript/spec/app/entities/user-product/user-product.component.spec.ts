import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { UserProductComponent } from 'app/entities/user-product/user-product.component';
import { UserProductService } from 'app/entities/user-product/user-product.service';
import { UserProduct } from 'app/shared/model/user-product.model';

describe('Component Tests', () => {
  describe('UserProduct Management Component', () => {
    let comp: UserProductComponent;
    let fixture: ComponentFixture<UserProductComponent>;
    let service: UserProductService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UserProductComponent],
        providers: []
      })
        .overrideTemplate(UserProductComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserProductComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserProductService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UserProduct(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.userProducts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
