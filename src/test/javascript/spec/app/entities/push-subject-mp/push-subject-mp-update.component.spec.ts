import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { PushSubjectMpUpdateComponent } from 'app/entities/push-subject-mp/push-subject-mp-update.component';
import { PushSubjectMpService } from 'app/entities/push-subject-mp/push-subject-mp.service';
import { PushSubjectMp } from 'app/shared/model/push-subject-mp.model';

describe('Component Tests', () => {
  describe('PushSubjectMp Management Update Component', () => {
    let comp: PushSubjectMpUpdateComponent;
    let fixture: ComponentFixture<PushSubjectMpUpdateComponent>;
    let service: PushSubjectMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [PushSubjectMpUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PushSubjectMpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PushSubjectMpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PushSubjectMpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PushSubjectMp(123);
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
        const entity = new PushSubjectMp();
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
