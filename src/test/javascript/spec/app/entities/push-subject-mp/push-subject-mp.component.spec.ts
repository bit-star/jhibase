import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { PushSubjectMpComponent } from 'app/entities/push-subject-mp/push-subject-mp.component';
import { PushSubjectMpService } from 'app/entities/push-subject-mp/push-subject-mp.service';
import { PushSubjectMp } from 'app/shared/model/push-subject-mp.model';

describe('Component Tests', () => {
  describe('PushSubjectMp Management Component', () => {
    let comp: PushSubjectMpComponent;
    let fixture: ComponentFixture<PushSubjectMpComponent>;
    let service: PushSubjectMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [PushSubjectMpComponent]
      })
        .overrideTemplate(PushSubjectMpComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PushSubjectMpComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PushSubjectMpService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PushSubjectMp(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.pushSubjects && comp.pushSubjects[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
