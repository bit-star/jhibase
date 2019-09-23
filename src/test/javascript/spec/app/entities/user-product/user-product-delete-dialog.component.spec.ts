import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhibaseTestModule } from '../../../test.module';
import { UserProductDeleteDialogComponent } from 'app/entities/user-product/user-product-delete-dialog.component';
import { UserProductService } from 'app/entities/user-product/user-product.service';

describe('Component Tests', () => {
  describe('UserProduct Management Delete Component', () => {
    let comp: UserProductDeleteDialogComponent;
    let fixture: ComponentFixture<UserProductDeleteDialogComponent>;
    let service: UserProductService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UserProductDeleteDialogComponent]
      })
        .overrideTemplate(UserProductDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UserProductDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UserProductService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
