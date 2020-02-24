import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { ServiceWindowMpComponent } from 'app/entities/service-window-mp/service-window-mp.component';
import { ServiceWindowMpService } from 'app/entities/service-window-mp/service-window-mp.service';
import { ServiceWindowMp } from 'app/shared/model/service-window-mp.model';

describe('Component Tests', () => {
  describe('ServiceWindowMp Management Component', () => {
    let comp: ServiceWindowMpComponent;
    let fixture: ComponentFixture<ServiceWindowMpComponent>;
    let service: ServiceWindowMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [ServiceWindowMpComponent]
      })
        .overrideTemplate(ServiceWindowMpComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ServiceWindowMpComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ServiceWindowMpService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ServiceWindowMp(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.serviceWindows && comp.serviceWindows[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
