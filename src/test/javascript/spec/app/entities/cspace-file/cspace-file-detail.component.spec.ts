import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { CspaceFileDetailComponent } from 'app/entities/cspace-file/cspace-file-detail.component';
import { CspaceFile } from 'app/shared/model/cspace-file.model';

describe('Component Tests', () => {
  describe('CspaceFile Management Detail Component', () => {
    let comp: CspaceFileDetailComponent;
    let fixture: ComponentFixture<CspaceFileDetailComponent>;
    const route = ({ data: of({ cspaceFile: new CspaceFile(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [CspaceFileDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CspaceFileDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CspaceFileDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cspaceFile).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
