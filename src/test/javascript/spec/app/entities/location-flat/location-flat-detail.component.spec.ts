import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { LocationFlatDetailComponent } from 'app/entities/location-flat/location-flat-detail.component';
import { LocationFlat } from 'app/shared/model/location-flat.model';

describe('Component Tests', () => {
  describe('LocationFlat Management Detail Component', () => {
    let comp: LocationFlatDetailComponent;
    let fixture: ComponentFixture<LocationFlatDetailComponent>;
    const route = ({ data: of({ locationFlat: new LocationFlat(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [LocationFlatDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(LocationFlatDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(LocationFlatDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load locationFlat on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.locationFlat).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
