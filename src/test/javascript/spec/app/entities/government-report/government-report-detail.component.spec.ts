import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { GovernmentReportDetailComponent } from 'app/entities/government-report/government-report-detail.component';
import { GovernmentReport } from 'app/shared/model/government-report.model';

describe('Component Tests', () => {
  describe('GovernmentReport Management Detail Component', () => {
    let comp: GovernmentReportDetailComponent;
    let fixture: ComponentFixture<GovernmentReportDetailComponent>;
    const route = ({ data: of({ governmentReport: new GovernmentReport(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [GovernmentReportDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GovernmentReportDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GovernmentReportDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.governmentReport).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
