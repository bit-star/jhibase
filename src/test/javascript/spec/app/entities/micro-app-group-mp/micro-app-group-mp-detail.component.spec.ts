import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { MicroAppGroupMpDetailComponent } from 'app/entities/micro-app-group-mp/micro-app-group-mp-detail.component';
import { MicroAppGroupMp } from 'app/shared/model/micro-app-group-mp.model';

describe('Component Tests', () => {
  describe('MicroAppGroupMp Management Detail Component', () => {
    let comp: MicroAppGroupMpDetailComponent;
    let fixture: ComponentFixture<MicroAppGroupMpDetailComponent>;
    const route = ({ data: of({ microAppGroup: new MicroAppGroupMp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [MicroAppGroupMpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MicroAppGroupMpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MicroAppGroupMpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load microAppGroup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.microAppGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
