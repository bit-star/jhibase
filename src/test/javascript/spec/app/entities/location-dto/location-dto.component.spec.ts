import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { LocationDTOComponent } from 'app/entities/location-dto/location-dto.component';
import { LocationDTOService } from 'app/entities/location-dto/location-dto.service';
import { LocationDTO } from 'app/shared/model/location-dto.model';

describe('Component Tests', () => {
  describe('LocationDTO Management Component', () => {
    let comp: LocationDTOComponent;
    let fixture: ComponentFixture<LocationDTOComponent>;
    let service: LocationDTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [LocationDTOComponent],
        providers: []
      })
        .overrideTemplate(LocationDTOComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LocationDTOComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LocationDTOService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LocationDTO(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.locationDTOS && comp.locationDTOS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
