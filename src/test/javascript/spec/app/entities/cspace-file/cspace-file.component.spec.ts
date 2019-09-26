import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhibaseTestModule } from '../../../test.module';
import { CspaceFileComponent } from 'app/entities/cspace-file/cspace-file.component';
import { CspaceFileService } from 'app/entities/cspace-file/cspace-file.service';
import { CspaceFile } from 'app/shared/model/cspace-file.model';

describe('Component Tests', () => {
  describe('CspaceFile Management Component', () => {
    let comp: CspaceFileComponent;
    let fixture: ComponentFixture<CspaceFileComponent>;
    let service: CspaceFileService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [CspaceFileComponent],
        providers: []
      })
        .overrideTemplate(CspaceFileComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CspaceFileComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CspaceFileService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CspaceFile(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.cspaceFiles[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
