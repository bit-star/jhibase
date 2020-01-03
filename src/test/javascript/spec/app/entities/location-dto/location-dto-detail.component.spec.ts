import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { LocationDTODetailComponent } from 'app/entities/location-dto/location-dto-detail.component';
import { LocationDTO } from 'app/shared/model/location-dto.model';

describe('Component Tests', () => {
  describe('LocationDTO Management Detail Component', () => {
    let comp: LocationDTODetailComponent;
    let fixture: ComponentFixture<LocationDTODetailComponent>;
    const route = ({ data: of({ locationDTO: new LocationDTO(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [LocationDTODetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LocationDTODetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LocationDTODetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load locationDTO on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.locationDTO).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
