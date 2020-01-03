import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { LocationVMUpdateComponent } from 'app/entities/location-vm/location-vm-update.component';
import { LocationVMService } from 'app/entities/location-vm/location-vm.service';
import { LocationVM } from 'app/shared/model/location-vm.model';

describe('Component Tests', () => {
  describe('LocationVM Management Update Component', () => {
    let comp: LocationVMUpdateComponent;
    let fixture: ComponentFixture<LocationVMUpdateComponent>;
    let service: LocationVMService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [LocationVMUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(LocationVMUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(LocationVMUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(LocationVMService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new LocationVM(123);
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
        const entity = new LocationVM();
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
