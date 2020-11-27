import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { MicroAppGroupMpUpdateComponent } from 'app/entities/micro-app-group-mp/micro-app-group-mp-update.component';
import { MicroAppGroupMpService } from 'app/entities/micro-app-group-mp/micro-app-group-mp.service';
import { MicroAppGroupMp } from 'app/shared/model/micro-app-group-mp.model';

describe('Component Tests', () => {
  describe('MicroAppGroupMp Management Update Component', () => {
    let comp: MicroAppGroupMpUpdateComponent;
    let fixture: ComponentFixture<MicroAppGroupMpUpdateComponent>;
    let service: MicroAppGroupMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [MicroAppGroupMpUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MicroAppGroupMpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MicroAppGroupMpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MicroAppGroupMpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MicroAppGroupMp(123);
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
        const entity = new MicroAppGroupMp();
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
