import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { UucUserBaseinfoMpComponent } from 'app/entities/uuc-user-baseinfo-mp/uuc-user-baseinfo-mp.component';
import { UucUserBaseinfoMpService } from 'app/entities/uuc-user-baseinfo-mp/uuc-user-baseinfo-mp.service';
import { UucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';

describe('Component Tests', () => {
  describe('UucUserBaseinfoMp Management Component', () => {
    let comp: UucUserBaseinfoMpComponent;
    let fixture: ComponentFixture<UucUserBaseinfoMpComponent>;
    let service: UucUserBaseinfoMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UucUserBaseinfoMpComponent]
      })
        .overrideTemplate(UucUserBaseinfoMpComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UucUserBaseinfoMpComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UucUserBaseinfoMpService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UucUserBaseinfoMp(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.uucUserBaseinfos && comp.uucUserBaseinfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
