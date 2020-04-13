import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { ProcessMsgTaskMpDetailComponent } from 'app/entities/process-msg-task-mp/process-msg-task-mp-detail.component';
import { ProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';

describe('Component Tests', () => {
  describe('ProcessMsgTaskMp Management Detail Component', () => {
    let comp: ProcessMsgTaskMpDetailComponent;
    let fixture: ComponentFixture<ProcessMsgTaskMpDetailComponent>;
    const route = ({ data: of({ processMsgTask: new ProcessMsgTaskMp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [ProcessMsgTaskMpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ProcessMsgTaskMpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ProcessMsgTaskMpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load processMsgTask on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.processMsgTask).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
