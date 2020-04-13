import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { ProcessMsgTaskMpUpdateComponent } from 'app/entities/process-msg-task-mp/process-msg-task-mp-update.component';
import { ProcessMsgTaskMpService } from 'app/entities/process-msg-task-mp/process-msg-task-mp.service';
import { ProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';

describe('Component Tests', () => {
  describe('ProcessMsgTaskMp Management Update Component', () => {
    let comp: ProcessMsgTaskMpUpdateComponent;
    let fixture: ComponentFixture<ProcessMsgTaskMpUpdateComponent>;
    let service: ProcessMsgTaskMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [ProcessMsgTaskMpUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ProcessMsgTaskMpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProcessMsgTaskMpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProcessMsgTaskMpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ProcessMsgTaskMp(123);
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
        const entity = new ProcessMsgTaskMp();
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
