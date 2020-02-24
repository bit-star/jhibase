import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { FmpSubCompanyMpDetailComponent } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp-detail.component';
import { FmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';

describe('Component Tests', () => {
  describe('FmpSubCompanyMp Management Detail Component', () => {
    let comp: FmpSubCompanyMpDetailComponent;
    let fixture: ComponentFixture<FmpSubCompanyMpDetailComponent>;
    const route = ({ data: of({ fmpSubCompany: new FmpSubCompanyMp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [FmpSubCompanyMpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FmpSubCompanyMpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FmpSubCompanyMpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fmpSubCompany on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fmpSubCompany).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
