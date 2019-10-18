import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhibaseTestModule } from '../../../test.module';
import { HistorySearchDetailComponent } from 'app/entities/history-search/history-search-detail.component';
import { HistorySearch } from 'app/shared/model/history-search.model';

describe('Component Tests', () => {
  describe('HistorySearch Management Detail Component', () => {
    let comp: HistorySearchDetailComponent;
    let fixture: ComponentFixture<HistorySearchDetailComponent>;
    const route = ({ data: of({ historySearch: new HistorySearch(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhibaseTestModule],
        declarations: [HistorySearchDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(HistorySearchDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HistorySearchDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.historySearch).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
