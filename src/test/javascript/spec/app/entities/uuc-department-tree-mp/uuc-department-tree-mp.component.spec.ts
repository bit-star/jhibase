import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { UucDepartmentTreeMpComponent } from 'app/entities/uuc-department-tree-mp/uuc-department-tree-mp.component';
import { UucDepartmentTreeMpService } from 'app/entities/uuc-department-tree-mp/uuc-department-tree-mp.service';
import { UucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';

describe('Component Tests', () => {
  describe('UucDepartmentTreeMp Management Component', () => {
    let comp: UucDepartmentTreeMpComponent;
    let fixture: ComponentFixture<UucDepartmentTreeMpComponent>;
    let service: UucDepartmentTreeMpService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UucDepartmentTreeMpComponent]
      })
        .overrideTemplate(UucDepartmentTreeMpComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(UucDepartmentTreeMpComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(UucDepartmentTreeMpService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new UucDepartmentTreeMp(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.uucDepartmentTrees && comp.uucDepartmentTrees[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
