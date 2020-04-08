import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { PushSubjectMpDetailComponent } from 'app/entities/push-subject-mp/push-subject-mp-detail.component';
import { PushSubjectMp } from 'app/shared/model/push-subject-mp.model';

describe('Component Tests', () => {
  describe('PushSubjectMp Management Detail Component', () => {
    let comp: PushSubjectMpDetailComponent;
    let fixture: ComponentFixture<PushSubjectMpDetailComponent>;
    const route = ({ data: of({ pushSubject: new PushSubjectMp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [PushSubjectMpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PushSubjectMpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PushSubjectMpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load pushSubject on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.pushSubject).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
