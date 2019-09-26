import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { GovernmentReportComponent } from 'app/entities/government-report/government-report.component';
import { GovernmentReportService } from 'app/entities/government-report/government-report.service';
import { GovernmentReport } from 'app/shared/model/government-report.model';

describe('Component Tests', () => {
  describe('GovernmentReport Management Component', () => {
    let comp: GovernmentReportComponent;
    let fixture: ComponentFixture<GovernmentReportComponent>;
    let service: GovernmentReportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [GovernmentReportComponent],
        providers: []
      })
        .overrideTemplate(GovernmentReportComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GovernmentReportComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GovernmentReportService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new GovernmentReport(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.governmentReports[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
