import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { MsgReceiverGroupMpDetailComponent } from 'app/entities/msg-receiver-group-mp/msg-receiver-group-mp-detail.component';
import { MsgReceiverGroupMp } from 'app/shared/model/msg-receiver-group-mp.model';

describe('Component Tests', () => {
  describe('MsgReceiverGroupMp Management Detail Component', () => {
    let comp: MsgReceiverGroupMpDetailComponent;
    let fixture: ComponentFixture<MsgReceiverGroupMpDetailComponent>;
    const route = ({ data: of({ msgReceiverGroup: new MsgReceiverGroupMp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [MsgReceiverGroupMpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MsgReceiverGroupMpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MsgReceiverGroupMpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load msgReceiverGroup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.msgReceiverGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
