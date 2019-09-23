import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { UserProductUpdateComponent } from 'app/entities/user-product/user-product-update.component';
import { UserProductService } from 'app/entities/user-product/user-product.service';
import { UserProduct } from 'app/shared/model/user-product.model';

describe('Component Tests', () => {
  describe('UserProduct Management Update Component', () => {
    let comp: UserProductUpdateComponent;
    let fixture: ComponentFixture<UserProductUpdateComponent>;
    let service: UserProductService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UserProductUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UserProductUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UserProductUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserProductService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserProduct(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new UserProduct();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
