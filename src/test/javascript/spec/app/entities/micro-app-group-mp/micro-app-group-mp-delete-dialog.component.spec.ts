import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhibaseTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { MicroAppGroupMpDeleteDialogComponent } from 'app/entities/micro-app-group-mp/micro-app-group-mp-delete-dialog.component';
import { MicroAppGroupMpService } from 'app/entities/micro-app-group-mp/micro-app-group-mp.service';

describe('Component Tests', () => {
  describe('MicroAppGroupMp Management Delete Component', () => {
    let comp: MicroAppGroupMpDeleteDialogComponent;
    let fixture: ComponentFixture<MicroAppGroupMpDeleteDialogComponent>;
    let service: MicroAppGroupMpService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [MicroAppGroupMpDeleteDialogComponent],
      })
        .overrideTemplate(MicroAppGroupMpDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MicroAppGroupMpDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MicroAppGroupMpService);
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
