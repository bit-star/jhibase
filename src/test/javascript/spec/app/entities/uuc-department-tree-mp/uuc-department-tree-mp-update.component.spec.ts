import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { UucDepartmentTreeMpUpdateComponent } from 'app/entities/uuc-department-tree-mp/uuc-department-tree-mp-update.component';
import { UucDepartmentTreeMpService } from 'app/entities/uuc-department-tree-mp/uuc-department-tree-mp.service';
import { UucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';

describe('Component Tests', () => {
  describe('UucDepartmentTreeMp Management Update Component', () => {
    let comp: UucDepartmentTreeMpUpdateComponent;
    let fixture: ComponentFixture<UucDepartmentTreeMpUpdateComponent>;
    let service: UucDepartmentTreeMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UucDepartmentTreeMpUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(UucDepartmentTreeMpUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UucDepartmentTreeMpUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UucDepartmentTreeMpService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new UucDepartmentTreeMp(123);
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
        const entity = new UucDepartmentTreeMp();
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
