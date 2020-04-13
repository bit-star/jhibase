import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { ProcessMsgTaskMpComponent } from 'app/entities/process-msg-task-mp/process-msg-task-mp.component';
import { ProcessMsgTaskMpService } from 'app/entities/process-msg-task-mp/process-msg-task-mp.service';
import { ProcessMsgTaskMp } from 'app/shared/model/process-msg-task-mp.model';

describe('Component Tests', () => {
  describe('ProcessMsgTaskMp Management Component', () => {
    let comp: ProcessMsgTaskMpComponent;
    let fixture: ComponentFixture<ProcessMsgTaskMpComponent>;
    let service: ProcessMsgTaskMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [ProcessMsgTaskMpComponent]
      })
        .overrideTemplate(ProcessMsgTaskMpComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ProcessMsgTaskMpComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ProcessMsgTaskMpService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ProcessMsgTaskMp(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.processMsgTasks && comp.processMsgTasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
