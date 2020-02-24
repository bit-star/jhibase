import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { FmpSubCompanyMpUpdateComponent } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp-update.component';
import { FmpSubCompanyMpService } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp.service';
import { FmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';

describe('Component Tests', () => {
  describe('FmpSubCompanyMp Management Update Component', () => {
    let comp: FmpSubCompanyMpUpdateComponent;
    let fixture: ComponentFixture<FmpSubCompanyMpUpdateComponent>;
    let service: FmpSubCompanyMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [FmpSubCompanyMpUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FmpSubCompanyMpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FmpSubCompanyMpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FmpSubCompanyMpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FmpSubCompanyMp(123);
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
        const entity = new FmpSubCompanyMp();
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
