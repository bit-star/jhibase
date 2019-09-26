import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { GovernmentReportUpdateComponent } from 'app/entities/government-report/government-report-update.component';
import { GovernmentReportService } from 'app/entities/government-report/government-report.service';
import { GovernmentReport } from 'app/shared/model/government-report.model';

describe('Component Tests', () => {
  describe('GovernmentReport Management Update Component', () => {
    let comp: GovernmentReportUpdateComponent;
    let fixture: ComponentFixture<GovernmentReportUpdateComponent>;
    let service: GovernmentReportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [GovernmentReportUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(GovernmentReportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GovernmentReportUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GovernmentReportService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GovernmentReport(123);
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
        const entity = new GovernmentReport();
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
