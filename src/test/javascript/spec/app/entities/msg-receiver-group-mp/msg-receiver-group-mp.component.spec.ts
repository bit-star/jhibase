import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { MsgReceiverGroupMpComponent } from 'app/entities/msg-receiver-group-mp/msg-receiver-group-mp.component';
import { MsgReceiverGroupMpService } from 'app/entities/msg-receiver-group-mp/msg-receiver-group-mp.service';
import { MsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';

describe('Component Tests', () => {
  describe('MsgReceiverGroupMp Management Component', () => {
    let comp: MsgReceiverGroupMpComponent;
    let fixture: ComponentFixture<MsgReceiverGroupMpComponent>;
    let service: MsgReceiverGroupMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [MsgReceiverGroupMpComponent]
      })
        .overrideTemplate(MsgReceiverGroupMpComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MsgReceiverGroupMpComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MsgReceiverGroupMpService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MsgReceiverGroupMp(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.msgReceiverGroups && comp.msgReceiverGroups[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
