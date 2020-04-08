import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhibaseTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { UucDepartmentTreeMpDeleteDialogComponent } from 'app/entities/uuc-department-tree-mp/uuc-department-tree-mp-delete-dialog.component';
import { UucDepartmentTreeMpService } from 'app/entities/uuc-department-tree-mp/uuc-department-tree-mp.service';

describe('Component Tests', () => {
  describe('UucDepartmentTreeMp Management Delete Component', () => {
    let comp: UucDepartmentTreeMpDeleteDialogComponent;
    let fixture: ComponentFixture<UucDepartmentTreeMpDeleteDialogComponent>;
    let service: UucDepartmentTreeMpService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UucDepartmentTreeMpDeleteDialogComponent]
      })
        .overrideTemplate(UucDepartmentTreeMpDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UucDepartmentTreeMpDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UucDepartmentTreeMpService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
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
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
