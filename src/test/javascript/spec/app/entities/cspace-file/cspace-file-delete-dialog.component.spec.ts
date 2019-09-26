import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhibaseTestModule } from '../../../test.module';
import { CspaceFileDeleteDialogComponent } from 'app/entities/cspace-file/cspace-file-delete-dialog.component';
import { CspaceFileService } from 'app/entities/cspace-file/cspace-file.service';

describe('Component Tests', () => {
  describe('CspaceFile Management Delete Component', () => {
    let comp: CspaceFileDeleteDialogComponent;
    let fixture: ComponentFixture<CspaceFileDeleteDialogComponent>;
    let service: CspaceFileService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [CspaceFileDeleteDialogComponent]
      })
        .overrideTemplate(CspaceFileDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CspaceFileDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CspaceFileService);
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
