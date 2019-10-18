import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhibaseTestModule } from '../../../test.module';
import { HistorySearchDeleteDialogComponent } from 'app/entities/history-search/history-search-delete-dialog.component';
import { HistorySearchService } from 'app/entities/history-search/history-search.service';

describe('Component Tests', () => {
  describe('HistorySearch Management Delete Component', () => {
    let comp: HistorySearchDeleteDialogComponent;
    let fixture: ComponentFixture<HistorySearchDeleteDialogComponent>;
    let service: HistorySearchService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [HistorySearchDeleteDialogComponent]
      })
        .overrideTemplate(HistorySearchDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HistorySearchDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistorySearchService);
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
