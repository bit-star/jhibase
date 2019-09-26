import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { CspaceFileUpdateComponent } from 'app/entities/cspace-file/cspace-file-update.component';
import { CspaceFileService } from 'app/entities/cspace-file/cspace-file.service';
import { CspaceFile } from 'app/shared/model/cspace-file.model';

describe('Component Tests', () => {
  describe('CspaceFile Management Update Component', () => {
    let comp: CspaceFileUpdateComponent;
    let fixture: ComponentFixture<CspaceFileUpdateComponent>;
    let service: CspaceFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [CspaceFileUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CspaceFileUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CspaceFileUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CspaceFileService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CspaceFile(123);
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
        const entity = new CspaceFile();
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
