import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { FmpSubCompanyMpComponent } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp.component';
import { FmpSubCompanyMpService } from 'app/entities/fmp-sub-company-mp/fmp-sub-company-mp.service';
import { FmpSubCompanyMp } from 'app/shared/model/fmp-sub-company-mp.model';

describe('Component Tests', () => {
  describe('FmpSubCompanyMp Management Component', () => {
    let comp: FmpSubCompanyMpComponent;
    let fixture: ComponentFixture<FmpSubCompanyMpComponent>;
    let service: FmpSubCompanyMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [FmpSubCompanyMpComponent]
      })
        .overrideTemplate(FmpSubCompanyMpComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FmpSubCompanyMpComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FmpSubCompanyMpService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FmpSubCompanyMp(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fmpSubCompanies && comp.fmpSubCompanies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
