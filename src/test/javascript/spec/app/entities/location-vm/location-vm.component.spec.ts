import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { LocationVMComponent } from 'app/entities/location-vm/location-vm.component';
import { LocationVMService } from 'app/entities/location-vm/location-vm.service';
import { LocationVM } from 'app/shared/model/location-vm.model';

describe('Component Tests', () => {
  describe('LocationVM Management Component', () => {
    let comp: LocationVMComponent;
    let fixture: ComponentFixture<LocationVMComponent>;
    let service: LocationVMService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [LocationVMComponent],
        providers: []
      })
        .overrideTemplate(LocationVMComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LocationVMComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LocationVMService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new LocationVM(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.locationVMS && comp.locationVMS[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
