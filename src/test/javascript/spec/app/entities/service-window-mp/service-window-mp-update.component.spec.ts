import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { ServiceWindowMpUpdateComponent } from 'app/entities/service-window-mp/service-window-mp-update.component';
import { ServiceWindowMpService } from 'app/entities/service-window-mp/service-window-mp.service';
import { ServiceWindowMp } from 'app/shared/model/service-window-mp.model';

describe('Component Tests', () => {
  describe('ServiceWindowMp Management Update Component', () => {
    let comp: ServiceWindowMpUpdateComponent;
    let fixture: ComponentFixture<ServiceWindowMpUpdateComponent>;
    let service: ServiceWindowMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [ServiceWindowMpUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ServiceWindowMpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ServiceWindowMpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ServiceWindowMpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ServiceWindowMp(123);
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
        const entity = new ServiceWindowMp();
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
