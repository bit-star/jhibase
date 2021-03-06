import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { HistorySearchUpdateComponent } from 'app/entities/history-search/history-search-update.component';
import { HistorySearchService } from 'app/entities/history-search/history-search.service';
import { HistorySearch } from 'app/shared/model/history-search.model';

describe('Component Tests', () => {
  describe('HistorySearch Management Update Component', () => {
    let comp: HistorySearchUpdateComponent;
    let fixture: ComponentFixture<HistorySearchUpdateComponent>;
    let service: HistorySearchService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [HistorySearchUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(HistorySearchUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HistorySearchUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HistorySearchService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new HistorySearch(123);
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
        const entity = new HistorySearch();
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
