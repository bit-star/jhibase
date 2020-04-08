import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { UucUserBaseinfoMpDetailComponent } from 'app/entities/uuc-user-baseinfo-mp/uuc-user-baseinfo-mp-detail.component';
import { UucUserBaseinfoMp } from 'app/shared/model/uuc-user-baseinfo-mp.model';

describe('Component Tests', () => {
  describe('UucUserBaseinfoMp Management Detail Component', () => {
    let comp: UucUserBaseinfoMpDetailComponent;
    let fixture: ComponentFixture<UucUserBaseinfoMpDetailComponent>;
    const route = ({ data: of({ uucUserBaseinfo: new UucUserBaseinfoMp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UucUserBaseinfoMpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UucUserBaseinfoMpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UucUserBaseinfoMpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load uucUserBaseinfo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.uucUserBaseinfo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
