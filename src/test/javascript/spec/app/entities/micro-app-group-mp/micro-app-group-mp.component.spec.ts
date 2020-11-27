import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { MicroAppGroupMpComponent } from 'app/entities/micro-app-group-mp/micro-app-group-mp.component';
import { MicroAppGroupMpService } from 'app/entities/micro-app-group-mp/micro-app-group-mp.service';
import { MicroAppGroupMp } from 'app/shared/model/micro-app-group-mp.model';

describe('Component Tests', () => {
  describe('MicroAppGroupMp Management Component', () => {
    let comp: MicroAppGroupMpComponent;
    let fixture: ComponentFixture<MicroAppGroupMpComponent>;
    let service: MicroAppGroupMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [MicroAppGroupMpComponent],
      })
        .overrideTemplate(MicroAppGroupMpComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MicroAppGroupMpComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MicroAppGroupMpService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MicroAppGroupMp(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.microAppGroups && comp.microAppGroups[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
