import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { ServiceWindowMpDetailComponent } from 'app/entities/service-window-mp/service-window-mp-detail.component';
import { ServiceWindowMp } from 'app/shared/model/service-window-mp.model';

describe('Component Tests', () => {
  describe('ServiceWindowMp Management Detail Component', () => {
    let comp: ServiceWindowMpDetailComponent;
    let fixture: ComponentFixture<ServiceWindowMpDetailComponent>;
    const route = ({ data: of({ serviceWindow: new ServiceWindowMp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [ServiceWindowMpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ServiceWindowMpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ServiceWindowMpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load serviceWindow on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.serviceWindow).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
