import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistorySearch } from 'app/shared/model/history-search.model';

@Component({
  selector: 'jhi-history-search-detail',
  templateUrl: './history-search-detail.component.html'
})
export class HistorySearchDetailComponent implements OnInit {
  historySearch: IHistorySearch;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ historySearch }) => {
      this.historySearch = historySearch;
    });
  }

  previousState() {
    window.history.back();
  }
}
