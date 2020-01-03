import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { LocationFlatComponent } from 'app/entities/location-flat/location-flat.component';
import { LocationFlatService } from 'app/entities/location-flat/location-flat.service';
import { LocationFlat } from 'app/shared/model/location-flat.model';

describe('Component Tests', () => {
  describe('LocationFlat Management Component', () => {
    let comp: LocationFlatComponent;
    let fixture: ComponentFixture<LocationFlatComponent>;
    let service: LocationFlatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [LocationFlatComponent],
        providers: []
      })
        .overrideTemplate(LocationFlatComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LocationFlatComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LocationFlatService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LocationFlat(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.locationFlats && comp.locationFlats[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
