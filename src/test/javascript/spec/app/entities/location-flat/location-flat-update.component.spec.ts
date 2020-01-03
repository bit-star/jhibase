import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { LocationFlatUpdateComponent } from 'app/entities/location-flat/location-flat-update.component';
import { LocationFlatService } from 'app/entities/location-flat/location-flat.service';
import { LocationFlat } from 'app/shared/model/location-flat.model';

describe('Component Tests', () => {
  describe('LocationFlat Management Update Component', () => {
    let comp: LocationFlatUpdateComponent;
    let fixture: ComponentFixture<LocationFlatUpdateComponent>;
    let service: LocationFlatService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [LocationFlatUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LocationFlatUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LocationFlatUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LocationFlatService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LocationFlat(123);
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
        const entity = new LocationFlat();
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
