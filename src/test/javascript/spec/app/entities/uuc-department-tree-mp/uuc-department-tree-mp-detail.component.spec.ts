import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { UucDepartmentTreeMpDetailComponent } from 'app/entities/uuc-department-tree-mp/uuc-department-tree-mp-detail.component';
import { UucDepartmentTreeMp } from 'app/shared/model/uuc-department-tree-mp.model';

describe('Component Tests', () => {
  describe('UucDepartmentTreeMp Management Detail Component', () => {
    let comp: UucDepartmentTreeMpDetailComponent;
    let fixture: ComponentFixture<UucDepartmentTreeMpDetailComponent>;
    const route = ({ data: of({ uucDepartmentTree: new UucDepartmentTreeMp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [UucDepartmentTreeMpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(UucDepartmentTreeMpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(UucDepartmentTreeMpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load uucDepartmentTree on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.uucDepartmentTree).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
