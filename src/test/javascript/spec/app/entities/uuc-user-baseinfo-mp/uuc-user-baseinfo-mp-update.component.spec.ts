import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { UucUserBaseinfoMpUpdateComponent } from 'app/entities/uuc-user-baseinfo-mp/uuc-user-baseinfo-mp-update.component';
import { UucUserBaseinfoMpService } from 'app/entities/uuc-user-baseinfo-mp/uuc-user-baseinfo-mp.service';
import { UucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';

describe('Component Tests', () => {
  describe('UucUserBaseinfoMp Management Update Component', () => {
    let comp: UucUserBaseinfoMpUpdateComponent;
    let fixture: ComponentFixture<UucUserBaseinfoMpUpdateComponent>;
    let service: UucUserBaseinfoMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UucUserBaseinfoMpUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UucUserBaseinfoMpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UucUserBaseinfoMpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UucUserBaseinfoMpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UucUserBaseinfoMp(123);
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
        const entity = new UucUserBaseinfoMp();
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
