import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhibaseTestModule } from '../../../test.module';
import { GovernmentReportDeleteDialogComponent } from 'app/entities/government-report/government-report-delete-dialog.component';
import { GovernmentReportService } from 'app/entities/government-report/government-report.service';

describe('Component Tests', () => {
  describe('GovernmentReport Management Delete Component', () => {
    let comp: GovernmentReportDeleteDialogComponent;
    let fixture: ComponentFixture<GovernmentReportDeleteDialogComponent>;
    let service: GovernmentReportService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [GovernmentReportDeleteDialogComponent]
      })
        .overrideTemplate(GovernmentReportDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GovernmentReportDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GovernmentReportService);
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
