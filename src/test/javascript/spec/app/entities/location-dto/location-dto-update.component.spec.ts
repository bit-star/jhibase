import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { LocationDTOUpdateComponent } from 'app/entities/location-dto/location-dto-update.component';
import { LocationDTOService } from 'app/entities/location-dto/location-dto.service';
import { LocationDTO } from 'app/shared/model/location-dto.model';

describe('Component Tests', () => {
  describe('LocationDTO Management Update Component', () => {
    let comp: LocationDTOUpdateComponent;
    let fixture: ComponentFixture<LocationDTOUpdateComponent>;
    let service: LocationDTOService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [LocationDTOUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LocationDTOUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LocationDTOUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LocationDTOService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LocationDTO(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new LocationDTO();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
